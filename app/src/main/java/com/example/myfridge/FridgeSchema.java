package com.example.myfridge;

import android.os.Build;
import android.provider.BaseColumns;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FridgeSchema implements BaseColumns{
    public static final String TABLE_NAME = "FridgeTable";
    public static final String BARCODE = "barcode";
    public static final LocalDate EXPIRATION_DATE = LocalDate.now();
    public static final int AMOUNT = 1;
    public static final int MINIMUM = 1;
}
