package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.BaseColumns;
//
import androidx.annotation.RequiresApi;

import java.util.LinkedList;

public class Database {
    //Attributes

    //Methods
    public static Product getAllProducts()
    {
        return new Product("", "", "");
    }

    public static Fridge getFridgeContents()
    {
        return new Fridge();
    }

    public static void insertIntoDB(Context context)
    {

    }

    public static void readFromDB(Context context)
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insertToProductDatabase(Context context, Product product)
    {
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        long newRowId;
        //
        dbHelper = new DatabaseHelper(context);
        // set the DB in write mode
        db = dbHelper.getWritableDatabase();
        //
        // set values
        values = new ContentValues();
        values.put(ProductSchema.BARCODE, product.getProductBarcode());
        values.put(ProductSchema.NAME, product.getProductName());
        values.put(ProductSchema.STORE, product.getProductStore());
        // Insert the new row, returning the primary key value of the new row
        newRowId = db.insert(ProductSchema.TABLE_NAME, null, values);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> New Product data inserted at row ID: " + newRowId );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insertToFridgeDatabase(Context context, Product product, Integer amount, Integer minimum)
    {
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        long newRowId;
        //
        dbHelper = new DatabaseHelper(context);
        // set the DB in write mode
        db = dbHelper.getWritableDatabase();
        //
        // set values
        values = new ContentValues();
        values.put(FridgeSchema.BARCODE, product.getProductBarcode());
        values.put(String.valueOf(FridgeSchema.AMOUNT), amount);
        values.put(String.valueOf(FridgeSchema.MINIMUM), minimum);
        // Insert the new row, returning the primary key value of the new row
        newRowId = db.insert(ProductSchema.TABLE_NAME, null, values);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> New Product data inserted at row ID: " + newRowId );
    }

    public static void getAllProducts(Context context)
    {

    }



}
