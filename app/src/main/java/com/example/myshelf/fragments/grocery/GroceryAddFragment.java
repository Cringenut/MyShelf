package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myshelf.R;
import com.example.myshelf.databases.grocery.DateConverter;
import com.example.myshelf.databinding.FragmentGroceryAddBinding;
import com.example.myshelf.viewmodels.groceries.GroceriesRepositoryViewModelFactory;
import com.example.myshelf.viewmodels.groceries.GroceryAddViewModel;

public class GroceryAddFragment extends Fragment {

    private FragmentGroceryAddBinding binding;
    private NavController navController;
    private GroceryAddViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigation
        navController = NavHostFragment.findNavController(GroceryAddFragment.this);
        navigation();

        // Using factory to pass repository and new Grocery as parameter to ViewModel
        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(GroceryAddViewModel.class);

        // Setting the text for buttons
        binding.btnChangeName.setText(viewModel.getGroceryToAdd().getGroceryName());
        binding.btnChangeExpirationDate.setText(DateConverter.dateToString(viewModel.getGroceryToAdd().getGroceryExpirationDate()));

        // Data observers
        viewModel.getGroceryName().observe(getViewLifecycleOwner(),
                newName -> binding.btnChangeName.setText(newName));
        viewModel.getGroceryExpirationDate().observe(getViewLifecycleOwner(),
                newExpirationDate -> binding.btnChangeExpirationDate.setText(DateConverter.dateToString(newExpirationDate)));

        // Return to previous fragment and add Grocery to database
        binding.btnAddGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addGrocery();
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        String currentName = viewModel.getGroceryToAdd().getGroceryName();
        binding.btnChangeName.setText(currentName);
        String currentExpirationDate = DateConverter.dateToString(viewModel.getGroceryToAdd().getGroceryExpirationDate());
        binding.btnChangeExpirationDate.setText(currentExpirationDate);
    }

    private void navigation() {
        // Navigate to name picker fragment
        binding.btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_groceryAddFragment_to_groceryChangeNameFragment);
            }
        });

        // Temporary later would navigate to some type of calendar
        binding.btnChangeExpirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_groceryAddFragment_to_groceryCalendarFragment);
            }
        });


    }

}