package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myshelf.R;
import com.example.myshelf.databases.grocery.DateConverter;
import com.example.myshelf.databinding.FragmentGroceryManipulationBinding;
import com.example.myshelf.objects.Grocery;
import com.example.myshelf.viewmodels.groceries.GroceriesListViewModel;
import com.example.myshelf.viewmodels.groceries.GroceriesRepositoryViewModelFactory;
import com.example.myshelf.viewmodels.groceries.GroceryManipulationViewModel;

public class GroceryEditFragment extends Fragment {

    private FragmentGroceryManipulationBinding binding;
    private NavController navController;
    private GroceryManipulationViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigation
        navController = NavHostFragment.findNavController(GroceryEditFragment.this);
        navigation();


        Grocery selectedGrocery = new ViewModelProvider(requireActivity())
                .get(GroceriesListViewModel.class)
                .getSelectedGrocery()
                .getValue();
        // Using factory to pass repository and new Grocery as parameter to ViewModel
        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext(), selectedGrocery);
        viewModel = new ViewModelProvider(requireActivity(), factory).get(GroceryManipulationViewModel.class);

        // Setting the text for buttons
        binding.btnChangeName.setText(viewModel.getGrocery().getGroceryName());
        binding.btnChangeExpirationDate.setText(DateConverter.dateToString(viewModel.getGrocery().getGroceryExpirationDate()));
        binding.btnChangeAdditionDate.setText(DateConverter.dateToString(viewModel.getGrocery().getGroceryAdditionDate()));


        // Data observers
        viewModel.getGroceryName().observe(getViewLifecycleOwner(),
                newName -> binding.btnChangeName.setText(newName));
        viewModel.getGroceryExpirationDate().observe(getViewLifecycleOwner(),
                newExpirationDate -> binding.btnChangeExpirationDate.setText(DateConverter.dateToString(newExpirationDate)));

        // Return to previous fragment and add Grocery to database
        binding.btnConfirm.setOnClickListener(v -> {
            viewModel.editGrocery();
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String currentName = viewModel.getGrocery().getGroceryName();
        binding.btnChangeName.setText(currentName);
        String currentExpirationDate = DateConverter.dateToString(viewModel.getGrocery().getGroceryExpirationDate());
        binding.btnChangeExpirationDate.setText(currentExpirationDate);
    }

    private void navigation() {
        // Navigate to name picker fragment
        binding.btnChangeName.setOnClickListener(v -> navController.navigate(R.id.action_groceryAddFragment_to_groceryChangeNameFragment));
        //
        binding.btnChangeExpirationDate.setOnClickListener(v -> navController.navigate(R.id.action_groceryAddFragment_to_groceryExpirationCalendarFragment));
        binding.btnChangeAdditionDate.setOnClickListener(v -> navController.navigate(R.id.action_groceryAddFragment_to_groceryAdditionCalendarViewModel));


    }

}
