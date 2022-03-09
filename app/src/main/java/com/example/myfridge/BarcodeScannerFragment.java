package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfridge.databinding.FragmentBarcodeScannerBinding;
import com.example.myfridge.databinding.FragmentExistingProductBinding;
import com.example.myfridge.databinding.FragmentFirstBinding;


public class BarcodeScannerFragment extends Fragment {

    private FragmentBarcodeScannerBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBarcodeScannerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAddNewProductScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BarcodeScannerFragment.this)
                        .navigate(R.id.action_barcodeScannerFragment_to_newProductFragment);
            }
        });

        binding.buttonAddExistingProductScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BarcodeScannerFragment.this)
                        .navigate(R.id.action_barcodeScannerFragment_to_existingProductFragment);
            }
        });

    }
}