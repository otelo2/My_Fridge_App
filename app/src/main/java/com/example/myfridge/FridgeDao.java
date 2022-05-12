package com.example.myfridge;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfridge.Fridge;
import com.example.myfridge.Product;

import java.util.List;

@Dao
public interface FridgeDao {
    @Query("SELECT * FROM fridge")
    List<Fridge> getAllActiveProducts();

    @Query("SELECT * FROM fridge WHERE product_barcode LIKE :barcode LIMIT 1")
    Fridge findByBarcode(String barcode);

    @Query("SELECT name FROM fridge JOIN product ON product_barcode = barcode WHERE barcode LIKE :barcode LIMIT 1")
    String getProductNameFromBarcode(String barcode);

    @Query("SELECT * FROM fridge JOIN product ON product_barcode = barcode WHERE barcode LIKE :barcode LIMIT 1")
    Fridge findAllDataByBarcode(String barcode);

    @Query("DELETE FROM fridge")
    void NukeAll();

    @Insert
    void insertFridge(Fridge... fridges);

    @Update
    void updateFridge(Fridge fridge);

    @Delete
    void delete(Fridge fridge);
}
