package com.iteso.sesion9.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.iteso.sesion9.database.DataBaseHandler;

public class StoreProvider extends ContentProvider {


    private static final String uri = "content://storeProvider/store";
    public static final Uri CONTENT_URI = Uri.parse(uri);
    private DataBaseHandler dataBaseHandler;
    private static final String BD_NOMBRE = "MyStore";
    private static final int BD_VERSION = 1;
    private static final String TABLA_Stores = "store";

    private SQLiteDatabase db;


    static final String _ID = "idStore";
    static final String COL_NOMBRE = "name";
    static final String COL_PHONE = "phone";
    static final String COL_CITY = "idCity";
    static final String COL_THUMB = "thumbnail";
    static final String COL_LAT = "latitude";
    static final String COL_LONG = "longitude";


    @Override
    public boolean onCreate() {

        dataBaseHandler = new DataBaseHandler(getContext());

        db = dataBaseHandler.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return null;
    }


    @Override
    public String getType(Uri uri) {
        return "";
    }
    //}


    @Override
    public Uri insert(Uri uri, ContentValues values) {


        long regId = db.insert(TABLA_Stores, "", values);

        if (regId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, regId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
