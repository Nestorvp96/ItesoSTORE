package com.iteso.sesion9.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.iteso.sesion9.database.DataBaseHandler;

import java.util.HashMap;

public class ProductProvider extends ContentProvider {

    private static final String uri = "content://productProvider/product";
    public static final Uri CONTENT_URI = Uri.parse(uri);
    private DataBaseHandler dataBaseHandler;
    private static final String BD_NOMBRE = "MyStore";
    private static final int BD_VERSION = 1;
    private static final String TABLA_productos = "product";

   private SQLiteDatabase db;

    //UriMatcher
    private static final int PRODUCTOS = 1;
    private static final int PRODUCTOS_ID = 2;
    private static final UriMatcher uriMatcher;

    static final String _ID = "idProduct";
     static final String COL_NOMBRE = "name";
     static final String COL_DESCRIPCION = "descriptionTEXT";
     static final String COL_IMAGE = "image";
     static final String COL_IDCATEGORY = "idCategory";
     static final String COL_IDSTORE = "idStore";

    private static HashMap<String, String> PRODUCTS_PROJECTION_MAP;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("productProvider", "product", PRODUCTOS);
        uriMatcher.addURI("productProvider", "product/#", PRODUCTOS_ID);
    }

    @Override
    public boolean onCreate() {

        dataBaseHandler = new DataBaseHandler(getContext());

        db = dataBaseHandler.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        /*String where = selection;
        if(uriMatcher.match(uri) == PRODUCTOS_ID){
            where = "idProduct=" + uri.getLastPathSegment();
        }



        Cursor c = db.query(TABLA_productos, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;*/

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLA_productos);

        switch (uriMatcher.match(uri)) {
            case PRODUCTOS:
                qb.setProjectionMap(PRODUCTS_PROJECTION_MAP);
                break;

            case PRODUCTOS_ID:
                qb.appendWhere( COL_IDCATEGORY + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = COL_NOMBRE;
        }

        ///////////////where clause///////////////////
        /*String where = selection;
        if(uriMatcher.match(uri) == PRODUCTOS_ID){
            where = "idCategory=" + uri.getLastPathSegment();
        }*/
/////////////////////////////////////////////////////////////////////////////
        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case PRODUCTOS:
                return "vnd.android.cursor.dir/vnd.example.product";
            /**
             * Get a particular student
             */
            case PRODUCTOS_ID:
                return "vnd.android.cursor.item/vnd.example.product";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    //}


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //long regId = 1;

        //SQLiteDatabase db = dataBaseHandler.getWritableDatabase();

        long regId = db.insert(TABLA_productos, "", values);

        if (regId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, regId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PRODUCTOS:
                count = db.delete(TABLA_productos, selection, selectionArgs);
                break;

            case PRODUCTOS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( TABLA_productos, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCTOS:
                count = db.update(TABLA_productos, values, selection, selectionArgs);
                break;

            case PRODUCTOS_ID:
                count = db.update(TABLA_productos, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /*public static final class ItemProduct implements BaseColumns
    {
        private ItemProduct() {}

        //Nombres de columnas
        public static final String COL_NOMBRE = "name";
        public static final String COL_DESCRIPCION = "descriptionTEXT";
        public static final String COL_IMAGE = "image";
        public static final String COL_IDCATEGORY = "idCategory";
        public static final String COL_IDSTORE = "idStore";
    }*/

}
