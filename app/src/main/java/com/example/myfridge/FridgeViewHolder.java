package com.example.myfridge;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class FridgeViewHolder extends RecyclerView.ViewHolder{

    private TextView productItemView;
    private TextView barcodeItemView;
    private TextView dateItemView;
    private TextView amountAndMinimumItemView;
    private Button decreaseAmount;
    private Button increaseAmount;

    public FridgeViewHolder(@NonNull View itemView) {
        super(itemView);
        productItemView = itemView.findViewById(R.id.textViewFridgeName);
        barcodeItemView = itemView.findViewById(R.id.textViewFridgeBarcode);
        dateItemView = itemView.findViewById(R.id.textViewFridgeDate);
        amountAndMinimumItemView = itemView.findViewById(R.id.textViewFridgeAmountAndMinimum);
        decreaseAmount = itemView.findViewById(R.id.buttonDecreaseAmount);
        increaseAmount = itemView.findViewById(R.id.buttonIncreaseAmount);

    }


    public void bind(String barcode, String date, String amount, String minimum){
        String name = getProductName(barcode);
        productItemView.setText(" "+name);
        barcodeItemView.setText(barcode);
        dateItemView.setText(date);
        amountAndMinimumItemView.setText(""+amount+" / "+minimum);

        decreaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease the amount of the product in the fridge
                decreaseAmountInFridge(barcodeItemView.getText().toString());

                // Reload the fragment
                Navigation.findNavController(itemView).navigate(R.id.action_FirstFragment_self);
            }
        });

        increaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase the amount of the product in the fridge
                increaseAmountInFridge(barcodeItemView.getText().toString());

                // Reload the fragment
                Navigation.findNavController(itemView).navigate(R.id.action_FirstFragment_self);
            }
        });

    }

    static FridgeViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_fridge, parent, false);

        return new FridgeViewHolder(view);
    }

    public void decreaseAmountInFridge(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        Integer current = Integer.valueOf(fridge.amount);
        current = current - 1;

        //If we removed all products from the fridge, delete it
        if (current <=0)
            deleteProductFromFridge(barcode);
        else {
            // Update the fridge
            fridge.amount = String.valueOf(current);
            db.fridgeDao().updateFridge(fridge);

            Log.i("DB_DELETE", "decreaseAmountInFridge: Decreased amount in fridge of: " + barcode);
        }


    }

    public void increaseAmountInFridge(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        Integer current = Integer.valueOf(fridge.amount);
        current = current + 1;

        //If we removed all products from the fridge, delete it
        if (current <=0)
            deleteProductFromFridge(barcode);
        else {
            // Update the fridge
            fridge.amount = String.valueOf(current);
            db.fridgeDao().updateFridge(fridge);

            Log.i("DB_DELETE", "increaseAmountInFridge: Increased amount in fridge of: " + barcode);
        }


    }

    public void deleteProductFromFridge(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        db.fridgeDao().delete(fridge);
        Log.i("DB_DELETE", "deleteProductFromFridge: BARCODE: " + barcode);
    }

    public String getProductName(String barcode){
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        return db.fridgeDao().getProductNameFromBarcode(barcode);
    };


}
