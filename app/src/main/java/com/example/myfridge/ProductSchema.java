package com.example.myfridge;

import android.provider.BaseColumns;
import java.time.LocalDate;

public class ProductSchema implements BaseColumns{
    public static final String TABLE_NAME = "ProductTable";
    public static final String BARCODE = "barcode";
    public static final String NAME = "name";
    public static final String STORE = "store";
}
