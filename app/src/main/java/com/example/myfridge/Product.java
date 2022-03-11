package com.example.myfridge;

public class Product {
    //Attributes
    public String barcode;
    public String name;
    public String store;

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

    public String getProductBarcode()
    {
        return this.barcode;
    }

    public String getProductName()
    {
        return this.name;
    }

    public String getProductStore()
    {
        return this.store;
    }

    public void getAllProducts()
    {
        Database.getAllProducts();
    }

    public void updateProduct()
    {

    }

}
