package com.example.myfridge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DatabaseHelper extends SQLiteOpenHelper{

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDatabase001.db";

    private static final String SQL_CREATE_ENTRIES_FRIDGE_SCHEMA =
            "CREATE TABLE " + FridgeSchema.TABLE_NAME + " (" +
                    FridgeSchema._ID + " INTEGER PRIMARY KEY," +
                    FridgeSchema.BARCODE + " TEXT," +
                    FridgeSchema.EXPIRATION_DATE + " TEXT," +
                    FridgeSchema.AMOUNT + " TEXT," +
                    FridgeSchema.MINIMUM + " INT)";

    private static final String SQL_DELETE_ENTRIES_FRIDGE_SCHEMA =
            "DROP TABLE IF EXISTS " + FridgeSchema.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_PRODUCT_SCHEMA =
            "CREATE TABLE " + ProductSchema.TABLE_NAME + " (" +
                    ProductSchema._ID + " INTEGER PRIMARY KEY," +
                    ProductSchema.BARCODE + " TEXT," +
                    ProductSchema.NAME + " TEXT," +
                    ProductSchema.STORE + " INT)";

    private static final String SQL_DELETE_ENTRIES_PRODUCT_SCHEMA =
            "DROP TABLE IF EXISTS " + ProductSchema.TABLE_NAME;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//end constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_FRIDGE_SCHEMA);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Creating Fridge Database");
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_PRODUCT_SCHEMA);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Creating Product Database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_FRIDGE_SCHEMA);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_PRODUCT_SCHEMA);
        onCreate(sqLiteDatabase);
        System.out.println(">>>>>>>>>>>>>>>>>>>> upgrading Database");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
        System.out.println(">>>>>>>>>>>>>>>>>>>> downgrading Database");
    }//end onDowngrade
}
