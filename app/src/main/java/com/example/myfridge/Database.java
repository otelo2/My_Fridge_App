package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
//
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

    public static void createFridgeDatabase(Context context)
    {

    }

    public static void createProductDatabase(Context context)
    {

    }

    public static void addToProductDatabase(Context context)
    {

    }

    public static void addToFridgeDatabase(Context context)
    {

    }

    public static void updateProductDatabase(Context context)
    {

    }

    public static void updateFridgeDatabase(Context context)
    {

    }

    public static void deleteFromProductDatabase(Context context)
    {

    }

    public static void deleteFromFridgeDatabase(Context context)
    {

    }

    public static void getAllProducts(Context context)
    {

    }



}
