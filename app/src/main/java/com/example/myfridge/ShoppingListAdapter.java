package com.example.myfridge;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ShoppingListAdapter extends ListAdapter<Fridge, ShoppingListViewHolder> {
    public ShoppingListAdapter(@NonNull DiffUtil.ItemCallback<Fridge> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShoppingListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {
        Fridge current = getItem(position);
        holder.bind(current.getProductBarcode());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Fridge> {

        @Override
        public boolean areItemsTheSame(@NonNull Fridge oldItem, @NonNull Fridge newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fridge oldItem, @NonNull Fridge newItem) {
            return oldItem.getProductBarcode().equals(newItem.getProductBarcode());
        }
    }

}
