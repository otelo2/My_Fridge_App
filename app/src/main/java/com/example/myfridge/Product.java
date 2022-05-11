package com.example.myfridge;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    //Attributes
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "barcode")
    public String barcode;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "store")
    public String store;

    //Methods
    public Product(){};

    public Product(String barcode, String name, String store) {
        this.barcode = barcode;
        this.name = name;
        this.store = store;
    }

    public Product createProduct(String barcode, String name, String store)
    {
        return new Product(barcode, name, store);
    }




    public void getAllProducts()
    {
        //Database.getAllProducts();
    }

    public void updateProduct()
    {

    }

    public String getProductName() {
        return ""+this.name+" from "+this.store+" ";
    }

    public String getProductBarcode() {
        return this.barcode;
    }
}
