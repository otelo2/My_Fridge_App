package com.example.myfridge;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class FridgeListAdapter extends ListAdapter<Fridge, FridgeViewHolder> {

    public FridgeListAdapter(@NonNull DiffUtil.ItemCallback<Fridge> diffCallback) {
        super(diffCallback);
    }

    @Override
    public FridgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FridgeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FridgeViewHolder holder, int position) {
        Fridge current = getItem(position);
        holder.bind(current.getProductBarcode(), current.getExpirationDate(), current.getAmount(), current.getMinimum());
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
