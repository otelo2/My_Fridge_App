package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfridge.databinding.FragmentAllProductsBinding;
import com.example.myfridge.databinding.FragmentExistingProductBinding;

public class AllProductsFragment extends Fragment {

    private FragmentAllProductsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAllProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonReturnToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AllProductsFragment.this)
                        .navigate(R.id.action_allProductsFragment_to_FirstFragment);
            }
        });

    }
}