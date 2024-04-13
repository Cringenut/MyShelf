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
import com.example.myshelf.activities.GroceriesActivity;
import com.example.myshelf.databases.grocery.DateConverter;
import com.example.myshelf.databinding.FragmentGroceryManipulationBinding;
import com.example.myshelf.viewmodels.groceries.GroceriesRepositoryViewModelFactory;
import com.example.myshelf.viewmodels.groceries.GroceryManipulationViewModel;
import com.example.myshelf.viewmodels.groceries.GrocerySelectViewModel;
import com.example.myshelf.viewscopes.GroceryEditViewModelScope;

public class GroceryEditFragment extends Fragment {

    private FragmentGroceryManipulationBinding binding;
    private NavController navController;
    private GroceryManipulationViewModel viewModel;
    private GrocerySelectViewModel grocerySelectViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryManipulationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigation
        navController = NavHostFragment.findNavController(GroceryEditFragment.this);
        navigation();

        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext());
        viewModel = new ViewModelProvider(requireActivity(), factory)
                .get(GroceryManipulationViewModel.class);

        GroceryEditViewModelScope scope = ((GroceriesActivity) getActivity())
                .getGroceryEditViewModelScope();
        grocerySelectViewModel = new ViewModelProvider(scope).get(GrocerySelectViewModel.class);
        grocerySelectViewModel.getSelectedGrocery().observe(getViewLifecycleOwner(), grocery -> {
            // Setting the text for buttons
            binding.btnChangeName.setText(grocery.getGroceryName());
            binding.btnChangeExpirationDate.setText(DateConverter.dateToString(grocery.getGroceryExpirationDate()));
            binding.btnChangeAdditionDate.setText(DateConverter.dateToString(grocery.getGroceryAdditionDate()));


            viewModel.setGrocery(grocery);
            // Data observers
            viewModel.getGroceryName().observe(getViewLifecycleOwner(),
                    newName -> binding.btnChangeName.setText(newName));
            viewModel.getGroceryExpirationDate().observe(getViewLifecycleOwner(),
                    newExpirationDate -> binding.btnChangeExpirationDate.setText(DateConverter.dateToString(newExpirationDate)));

            binding.btnConfirm.setOnClickListener(v -> {
                viewModel.editGrocery();
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            });
        });

    }

    private void navigation() {
        // Navigate to name picker fragment
        binding.btnChangeName.setOnClickListener(v -> navController.navigate(R.id.action_groceryEditFragment_to_groceryChangeNameFragment));
        //
        binding.btnChangeExpirationDate.setOnClickListener(v -> navController.navigate(R.id.action_groceryEditFragment_to_groceryExpirationCalendarFragment));
        binding.btnChangeAdditionDate.setOnClickListener(v -> navController.navigate(R.id.action_groceryEditFragment_to_groceryAdditionCalendarViewModel));
    }

}
