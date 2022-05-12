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

import com.example.myfridge.databinding.FragmentExistingProductBinding;
import com.example.myfridge.databinding.FragmentNewProductBinding;


public class ExistingProductFragment extends Fragment {

    private FragmentExistingProductBinding binding;
    String barcode;
    MyDatabase db = MyDatabase.getDbInstance(getActivity());

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentExistingProductBinding.inflate(inflater, container, false);

        // Receive the barcode from the barcode scanner fragment and update the text with the barcode
        getParentFragmentManager().setFragmentResultListener("dataFromBarcodeScanner", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                barcode = result.getString("barcode");

                // Update with the information of the product
                updateTextFields();

            }
        });

        if (barcode==null){
            barcode = ((MainActivity)getActivity()).getBarcode();

            // Update with the information of the product
            updateTextFields();
        }



        return binding.getRoot();

    }

    private void updateTextFields(){
        // Get the product
        Product product = db.productDao().findByBarcode(barcode);
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        // Get the fields to edit
        TextView barcodeText = binding.barcodeTextId3;
        EditText productName = binding.editTextExistingProductName;
        EditText productStore = binding.editTextExistingProductStore;
        EditText productDate = binding.editTextExistingDate;
        EditText productAmount = binding.editTextExistingProductAmount;
        EditText productMinimum = binding.editTextExistingProductMinimum;

        // Update the fields with the value from the db
        Log.i("PRODUCT", "updateTextFields: productName: "+ product.name);
        Log.i("PRODUCT", "updateTextFields: productName: "+ product.store);

        //Set all the values into the fields
        barcodeText.setText("Barcode: " + barcode);
        if (product != null){
            if (product.name != null)
                productName.setText(product.name);
            if (product.store != null)
                productStore.setText(product.store);
        }
        if (fridge != null){
            if (fridge.expirationDate != null)
                productDate.setText(fridge.expirationDate);
            if (fridge.amount != null)
                productAmount.setText(fridge.amount);
            if (fridge.minimum != null)
                productMinimum.setText(fridge.minimum);
        }

    }

    private void updateExistingProduct(){
        Product product = db.productDao().findByBarcode(barcode);
        Fridge fridge = db.fridgeDao().findByBarcode(barcode);

        // Get the fields with the data
        TextView barcodeText = binding.barcodeTextId3;
        EditText productName = binding.editTextExistingProductName;
        EditText productStore = binding.editTextExistingProductStore;
        EditText productDate = binding.editTextExistingDate;
        EditText productAmount = binding.editTextExistingProductAmount;
        EditText productMinimum = binding.editTextExistingProductMinimum;

        String name = productName.getText().toString();
        String store = productStore.getText().toString();
        String date = productDate.getText().toString();
        String amount = productAmount.getText().toString();
        String minimum = productMinimum.getText().toString();

        // Check if  the fridge exists
        if (fridge != null){
            fridge.expirationDate = date;
            fridge.amount = amount;
            fridge.minimum = minimum;

            db.fridgeDao().updateFridge(fridge);
        }
        else {
            Fridge fridgeNew = new Fridge();
            fridgeNew.productBarcode = barcode;
            fridgeNew.expirationDate = date;
            fridgeNew.amount = amount;
            fridgeNew.minimum = minimum;

            db.fridgeDao().insertFridge(fridgeNew);
        }

        product.name = name;
        product.store = store;
        db.productDao().updateProduct(product);

        Log.i("Database: ", "Updated product barcode:"+ barcode +". name: "+ name + ". store: "+ store);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAddExistingToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the product with the values of the fields
                updateExistingProduct();

                NavHostFragment.findNavController(ExistingProductFragment.this)
                        .navigate(R.id.action_existingProductFragment_to_FirstFragment);
            }
        });

    }
}