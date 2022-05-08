package com.example.myfridge;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product" + " WHERE barcode LIKE :barcode LIMIT 1")
    Product findByBarcode(String barcode);

    @Insert
    void insertProduct(Product... products);

    @Delete
    void delete(Product product);

}
