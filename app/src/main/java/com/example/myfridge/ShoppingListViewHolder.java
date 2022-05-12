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

public class ShoppingListViewHolder extends RecyclerView.ViewHolder{

    private TextView nameItemView;
    private TextView storeItemView;
    private TextView buyItemView;

    public ShoppingListViewHolder(@NonNull View itemView) {
        super(itemView);
        nameItemView = itemView.findViewById(R.id.textViewShoppingListName);
        storeItemView = itemView.findViewById(R.id.textViewShoppingListStore);
        buyItemView = itemView.findViewById(R.id.textViewShoppingListBuy);

    }


    public void bind(String barcode){
        String name = getProductName(barcode);
        String store = getProductStore(barcode);
        String toBuy = getHowManyToBuy(barcode);

        nameItemView.setText(name);
        storeItemView.setText(store);
        buyItemView.setText(toBuy);

    }

    static ShoppingListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_shoppinglist, parent, false);

        return new ShoppingListViewHolder(view);
    }

    public String getProductName(String barcode){
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        String name = db.fridgeDao().getProductNameFromBarcode(barcode);

        Log.i("DEBUG", "getProductName: " + name);
        return name;
    };

    public String getProductStore(String barcode){
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        String store = db.fridgeDao().getProductStoreFromBarcode(barcode);

        Log.i("DEBUG", "getProductStore: " + store);

        return store;
    };

    public String getHowManyToBuy(String barcode){
        MyDatabase db = MyDatabase.getDbInstance(itemView.getContext());
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        // Get values from DB
        String amount = fridge.amount; //db.fridgeDao().getProductAmountFromBarcode(barcode);

        String minimum = fridge.minimum; //db.fridgeDao().getProductMinimumFromBarcode(barcode);

        Log.i("HELP", "getHowManyToBuy: amount" + fridge.amount +" minimum: "+ fridge.minimum);

        Integer buyInteger = Integer.parseInt(minimum) - Integer.parseInt(amount);

        return Integer.toString(buyInteger);
    }

}
