package com.example.myfridge;

public class Product {
    //Attributes
    public static String barcode;
    public static String name;
    public static String store;

    //Methods
    public Product(String barcode, String name, String store) {
        this.barcode = barcode;
        this.name = name;
        this.store = store;
    }

    public Product createProduct(String barcode, String name, String store)
    {
        return new Product(barcode, name, store);
    }

    public static String getProductBarcode()
    {
        return barcode;
    }

    public static String getProductName()
    {
        return name;
    }

    public static String getProductStore()
    {
        return store;
    }

    public void getAllProducts()
    {
        Database.getAllProducts();
    }

    public void updateProduct()
    {

    }

}
