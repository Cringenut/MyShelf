package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myshelf.databinding.FragmentGroceryChangeNameBinding;


public class GroceryChangeNameFragment extends Fragment {

    private FragmentGroceryChangeNameBinding binding;
    private EditText textInputGroceryName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroceryChangeNameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputGroceryName = binding.textInputGroceryName;

        // Assuming there's a button in your layout to confirm the name change
        binding.btnConfirm.setOnClickListener(v -> {
            String newName = textInputGroceryName.getText().toString();
            // Use a method to pass this newName back to GroceryAddFragment. See Step 3 for options.
        });
    }

}