package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.database.DataBaseHandler;

import java.util.ArrayList;

public class ItemProductControl {


    public void addItemProduct(ItemProduct product, DataBaseHandler dh){


        SQLiteDatabase db = dh.getWritableDatabase();

        /*db.execSQL("INSERT INTO " +DataBaseHandler.TABLE_PRODUCT+ "(" +
                DataBaseHandler.KEY_PRODUCT_ID + "," +
                DataBaseHandler.KEY_PRODUCT_TITLE + "," +
                DataBaseHandler.KEY_PRODUCT_DESCRIPTION + "TEXT," +
                DataBaseHandler.KEY_PRODUCT_IMAGE + "," +
                DataBaseHandler.KEY_PRODUCT_CATEGORY + "," +
                DataBaseHandler.KEY_PRODUCT_STORE + ")" +
                "VALUES (" + product.getCode()+"," +
                product.getTitle()+"," +
                product.getDescription()+"," +
                product.getImage()+"," +
                product.getCategory()+"," +
                product.getStore()+ ")" );*/

        // Closing database connection
        try {

            ContentValues values = new ContentValues();
            values.put(DataBaseHandler.KEY_PRODUCT_ID, product.getCode());
            values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
            values.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION + "TEXT", product.getDescription());
            values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
            values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory());
            values.put(DataBaseHandler.KEY_PRODUCT_STORE, product.getStore());

            // Inserting Row
             db.insert(DataBaseHandler.TABLE_PRODUCT, null, values);

            db.close();
        } catch (Exception e) {}
        db = null;
        //values = null;



    }


    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){

        ArrayList<ItemProduct> products = new ArrayList<ItemProduct>();


        String selectQuery = "SELECT " + DataBaseHandler.KEY_PRODUCT_ID + ","
                + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + DataBaseHandler.KEY_PRODUCT_DESCRIPTION + "TEXT,"
                + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + DataBaseHandler.KEY_PRODUCT_CATEGORY + ","
                + DataBaseHandler.KEY_PRODUCT_STORE
                + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " WHERE "
                + DataBaseHandler.KEY_CATEGORY_ID + "= " + idCategory;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        try {
            while (cursor.moveToNext()) {
                ItemProduct product = new ItemProduct();
                product.setCode(cursor.getInt(0));
                product.setTitle(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setImage(cursor.getInt(3));
                product.setCategory(cursor.getInt(4));
                product.setStore(cursor.getInt(5));

                products.add(product);
            }


        } catch (Exception e) {}
        finally {
            cursor.close();
            db.close();
        }
        db = null;
        cursor = null;

        return products;
    }

}
