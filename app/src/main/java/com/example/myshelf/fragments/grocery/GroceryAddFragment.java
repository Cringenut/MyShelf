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
        navController = NavHostFragment.findNavController(GroceryAddFragment.this);


        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(GroceryAddViewModel.class);

        viewModel.getGroceryName().observe(getViewLifecycleOwner(), newName -> {
            binding.btnChangeName.setText(newName);
        });

        System.out.println("View");

        binding.btnAddGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addGrocery();
                navController.navigate(R.id.action_groceryAddFragment_back_to_groceriesFragment);
            }
        });
        binding.btnChangeName.setText(viewModel.getGroceryToAdd().getGroceryName());
        binding.btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_groceryAddFragment_to_groceryChangeNameFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String currentName = viewModel.getGroceryToAdd().getGroceryName();
        binding.btnChangeName.setText(currentName);
    }

}