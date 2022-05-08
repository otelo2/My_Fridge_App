package com.example.myfridge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfridge.databinding.FragmentBarcodeScannerBinding;
import com.example.myfridge.databinding.FragmentFirstBinding;
import com.example.myfridge.databinding.FragmentNewProductBinding;

public class NewProductFragment extends Fragment {

    private FragmentNewProductBinding binding;
    String barcode;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewProductBinding.inflate(inflater, container, false);

        // Receive the barcode from the barcode scanner fragment and update the text with the barcode
        getParentFragmentManager().setFragmentResultListener("dataFromBarcodeScanner", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                barcode = result.getString("barcode");

                TextView barcodeText = binding.barcodeTextId;
                barcodeText.setText("Barcode: " + barcode);

            }
        });

        return binding.getRoot();

    }

    private void saveNewProduct(String name, String store){
        MyDatabase db = MyDatabase.getDbInstance(getActivity());



        Product product = new Product();
        product.barcode = barcode;
        product.name = name;
        product.store = store;

        db.productDao().insertProduct(product);

        Log.i("Database: ", "Added product barcode:"+ barcode +". name: "+ name + ". store: "+ store);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        final EditText nameInput = binding.editTextProductName;
        final EditText storeInput = binding.editTextProductStore;
        final EditText dateInput = binding.editTextDate;
        final EditText amountInput = binding.editTextProductAmount;
        final EditText minimumInput = binding.editTextProductMinimum;

        binding.buttonAddNewToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Add the product data to the database
                saveNewProduct(nameInput.getText().toString(), storeInput.getText().toString());

                // Navigate to next screen
                NavHostFragment.findNavController(NewProductFragment.this)
                        .navigate(R.id.action_newProductFragment_to_FirstFragment);
            }
        });

    }
}