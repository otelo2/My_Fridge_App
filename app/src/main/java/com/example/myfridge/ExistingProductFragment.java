package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfridge.databinding.FragmentExistingProductBinding;
import com.example.myfridge.databinding.FragmentNewProductBinding;


public class ExistingProductFragment extends Fragment {

    private FragmentExistingProductBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentExistingProductBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAddExistingToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ExistingProductFragment.this)
                        .navigate(R.id.action_existingProductFragment_to_FirstFragment);
            }
        });

    }
}