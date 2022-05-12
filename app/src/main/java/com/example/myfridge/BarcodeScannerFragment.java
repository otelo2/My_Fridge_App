package com.example.myfridge;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myfridge.databinding.FragmentBarcodeScannerBinding;
import com.example.myfridge.databinding.FragmentExistingProductBinding;
import com.example.myfridge.databinding.FragmentFirstBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class BarcodeScannerFragment extends Fragment {

    private FragmentBarcodeScannerBinding binding;
    public String barcode;
    private Boolean productExists;

    public void openBarcodeScanner()
    {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(BarcodeScannerFragment.this);

        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scan product barcode");
        integrator.setBeepEnabled(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);


        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                barcode = result.getContents();
                Toast.makeText(getContext(), "Scanned : " + barcode, Toast.LENGTH_LONG).show();

                productExists = checkIfProductExists(result.getContents());

                if (productExists){
                    //Give the value of the barcode to the receiving fragment
                    Bundle args = new Bundle();
                    args.putString("barcode", barcode);
                    System.out.println(barcode);
                    getParentFragmentManager().setFragmentResult("dataFromBarcodeScanner", args);

                    MainActivity host = (MainActivity) getActivity();
                    host.setBarcode(barcode);

                    goToExistingProductFragment();
                }
                else{
                    //Give the value of the barcode to the receiving fragment
                    Bundle args = new Bundle();
                    args.putString("barcode", barcode);
                    System.out.println(barcode);
                    getParentFragmentManager().setFragmentResult("dataFromBarcodeScanner", args);

                    goToNewProductFragment();
                }

            }
        }
    }

    public Boolean checkIfProductExists(String barcode) {
        MyDatabase db = MyDatabase.getDbInstance(getActivity());
        Product product = db.productDao().findByBarcode(barcode);

        //Check if the product from the db isn't null, has a barcode, and it is the same as the one in the db
        if (product != null){
            if (product.barcode != null){
                if (product.barcode.equals(barcode)){
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public void goToNewProductFragment(){
        NavHostFragment.findNavController(BarcodeScannerFragment.this)
                .navigate(R.id.action_barcodeScannerFragment_to_newProductFragment);
    }

    public void goToExistingProductFragment(){
        NavHostFragment.findNavController(BarcodeScannerFragment.this)
                .navigate(R.id.action_barcodeScannerFragment_to_existingProductFragment);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBarcodeScannerBinding.inflate(inflater, container, false);

        View rootView = inflater.inflate (R.layout.fragment_barcode_scanner, container, false);
        ImageView imageQR = (ImageView)rootView.findViewById(R.id.image_qr);
        imageQR.setImageResource(R.drawable.qr_placeholder);

        openBarcodeScanner();

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