package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshelf.R;
import com.example.myshelf.adapters.grocery.GroceriesRecyclerViewAdapter;
import com.example.myshelf.databinding.FragmentGroceriesBinding;
import com.example.myshelf.objects.Grocery;
import com.example.myshelf.viewmodels.groceries.GroceriesListViewModel;
import com.example.myshelf.viewmodels.groceries.GroceriesRepositoryViewModelFactory;

public class GroceriesListFragment extends Fragment implements GroceriesRecyclerViewAdapter.OnGroceryClickListener {

    private FragmentGroceriesBinding binding;
    private NavController navController;
    private GroceriesListViewModel viewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // NavController
        navController = NavHostFragment
                .findNavController(GroceriesListFragment.this);
        navigation();

        // Using factory to pass repository as parameter to ViewModel
        GroceriesRepositoryViewModelFactory factory
                = new GroceriesRepositoryViewModelFactory(getContext());
        viewModel = new ViewModelProvider(this, factory).get(GroceriesListViewModel.class);

        // Setting up RecyclerView
        GroceriesRecyclerViewAdapter adapter = new GroceriesRecyclerViewAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.groceriesRecyclerView.setAdapter(adapter);
        binding.groceriesRecyclerView.setLayoutManager(layoutManager);

        // Observe groceries and update adapter if something changes
        viewModel.getGroceries().observe(getViewLifecycleOwner(), adapter::setGroceries);

        viewModel.getSelectedGrocery().observe(getViewLifecycleOwner(), grocery -> {
            // Handle the selected grocery, e.g., navigate to a detail view or show a Toast
            Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();
        });
        // Notify adapter to highlight the selected item
        viewModel.getSelectedGrocery().observe(getViewLifecycleOwner(), adapter::setSelectedGrocery);
    }

    private void navigation() {
        // Navigate to new Grocery creation fragment
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_groceriesFragment_to_groceryAddFragment);
            }
        });
    }

    @Override
    public void onGroceryClick(Grocery grocery) {
        viewModel.selectGrocery(grocery);
    }
}