package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myfridge.databinding.FragmentBarcodeScannerBinding;
import com.example.myfridge.databinding.FragmentShoppingListBinding;

import java.util.List;

public class ShoppingListFragment extends Fragment {

    private FragmentShoppingListBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentShoppingListBinding.inflate(inflater, container, false);

        View rootView = inflater.inflate (R.layout.fragment_barcode_scanner, container, false);
        ImageView imageQR = (ImageView)rootView.findViewById(R.id.image_qr);
        imageQR.setImageResource(R.drawable.qr_placeholder);

        RecyclerView recyclerView = binding.recyclerViewShoppingList;
        final ShoppingListAdapter adapter = new ShoppingListAdapter(new ShoppingListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyDatabase db = MyDatabase.getDbInstance(getActivity());
        List<Fridge> fridgeList = db.fridgeDao().getShoppingList();
        adapter.submitList(fridgeList);

        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonToFridgeFromShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShoppingListFragment.this)
                        .navigate(R.id.action_shoppingListFragment2_to_FirstFragment);
            }
        });

    }
}