package com.example.myfridge;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    //Attributes
    public abstract ProductDao productDao();
    public static MyDatabase INSTANCE;

    //Methods
    public static MyDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "PRODUCT_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }



}