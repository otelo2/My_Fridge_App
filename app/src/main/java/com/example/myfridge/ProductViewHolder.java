package com.example.myfridge;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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
        addProductToFridge = itemView.findViewById(R.id.button_add_to_fridge);

    }


    public void bind(String name, String barcode){
        productItemView.setText(name);
        barcodeItemView.setText(barcode);

        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the selected product
                deleteProductFromDatabase(barcodeItemView.getText().toString());

                Navigation.findNavController(itemView).navigate(R.id.action_allProductsFragment_self);
            }
        });
    }

    static ProductViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new ProductViewHolder(view);
    }

    public void deleteProductFromDatabase(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Product product = db.productDao().findByBarcode(barcode);

        db.productDao().delete(product);
        Log.i("DB_DELETE", "deleteProductFromDatabase: BARCODE: " + barcode);
    }



}
