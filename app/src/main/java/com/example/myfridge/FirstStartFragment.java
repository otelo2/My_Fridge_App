package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myfridge.databinding.FragmentFirstStartBinding;
import com.example.myfridge.databinding.FragmentShoppingListBinding;

public class FirstStartFragment extends Fragment {

    private FragmentFirstStartBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstStartBinding.inflate(inflater, container, false);

        View rootView = inflater.inflate (R.layout.fragment_barcode_scanner, container, false);
        ImageView imageQR = (ImageView)rootView.findViewById(R.id.image_qr);
        imageQR.setImageResource(R.drawable.qr_placeholder);

        // If you screw up the databases
        Boolean areYouSmart;
        areYouSmart = Boolean.TRUE;
        if (areYouSmart == Boolean.FALSE){
            // Delete all databases
            MyDatabase db = MyDatabase.getDbInstance(getActivity());
            db.productDao().NukeAll();
            db.fridgeDao().NukeAll();
            // Make the app crash
            String bye = null;
            Log.i("BYE :)", "onCreateView: " + bye);
        }

        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstStartFragment.this)
                        .navigate(R.id.action_firstStartFragment_to_FirstFragment);
            }
        });

    }
}