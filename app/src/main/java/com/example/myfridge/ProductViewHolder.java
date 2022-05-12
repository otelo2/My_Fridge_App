package com.example.myfridge;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private TextView productItemView;
    private TextView barcodeItemView;
    private Button deleteProductButton;
    private Button addProductToFridge;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productItemView = itemView.findViewById(R.id.textView_product);
        barcodeItemView = itemView.findViewById(R.id.productBarcodeText);
        deleteProductButton = itemView.findViewById(R.id.buttonDeleteProduct);
        addProductToFridge = itemView.findViewById(R.id.buttonAddToFridge);

    }


    public void bind(String name, String barcode){
        productItemView.setText(name);
        barcodeItemView.setText(barcode);

        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the selected product
                deleteProductFromDatabase(barcodeItemView.getText().toString());
                deleteProductFromFridge(barcodeItemView.getText().toString());

                // Reload fragment
                Navigation.findNavController(itemView).navigate(R.id.action_allProductsFragment_self);
            }
        });

        addProductToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send the barcode to the ExistingProductFragment
                MainActivity host = (MainActivity) view.getContext();
                host.setBarcode(barcode);
                Log.i("CONTEXT", "onClick: " + view.getContext().toString());
                Log.i("BARCODE", "onClick: " + barcode);

                // Go to the existing product fragment
                Navigation.findNavController(itemView).navigate(R.id.action_allProductsFragment_to_existingProductFragment);
            }
        });
    }

    static ProductViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_products, parent, false);

        return new ProductViewHolder(view);
    }

    public void deleteProductFromDatabase(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Product product = db.productDao().findByBarcode(barcode);

        db.productDao().delete(product);
        Log.i("DB_DELETE", "deleteProductFromDatabase: BARCODE: " + barcode);
    }

    public void deleteProductFromFridge(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        if (fridge != null)
        {
            db.fridgeDao().delete(fridge);
            Log.i("DB_DELETE", "deleteProductFromFridge: BARCODE: " + barcode);
        }
    }



}
