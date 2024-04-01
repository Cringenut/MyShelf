package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myshelf.databinding.FragmentGroceryChangeNameBinding;
import com.example.myshelf.viewmodels.groceries.GroceryAddViewModel;


public class GroceryChangeNameFragment extends Fragment {

    private FragmentGroceryChangeNameBinding binding;
    private GroceryAddViewModel viewModel;
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
        viewModel = new ViewModelProvider(requireActivity()).get(GroceryAddViewModel.class);
        // Setting name on confirm button click
        binding.btnConfirm.setOnClickListener(v -> {
            String newName = "Toast";
            viewModel.setGroceryName(newName);
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
    }

}