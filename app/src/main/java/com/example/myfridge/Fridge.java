package com.example.myfridge;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

// I think a better name would be something like "active product" or something.
@Entity
public class Fridge {
    //Attributes
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "product_barcode")
    public String productBarcode;

    @ColumnInfo(name = "expiration_date")
    public String expirationDate;

    @ColumnInfo(name = "amount")
    public String amount;

    @ColumnInfo(name = "minimum")
    public String minimum;


    //Methods
    public Fridge(){};

    public void addToFridge(Product product)
    {

    }

    public void readFromFridge(Product product)
    {

    }

    public void updateFridge()
    {

    }

    public void deleteFromFridge(Product product)
    {

    }
}
