package com.example.myshelf.viewmodels.groceries;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

public class GroceriesRepositoryViewModelFactory implements ViewModelProvider.Factory {
    private final GroceriesRepository groceriesRepository;
    // Encapsulating Repository creation
    public GroceriesRepositoryViewModelFactory(Context context) {
        groceriesRepository = new GroceriesRepository(context);
    }

    // Pass arguments to ViewModel
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GroceriesListViewModel.class)) {
            return (T) new GroceriesListViewModel(groceriesRepository);
        } else if (modelClass.isAssignableFrom(GroceryAddViewModel.class)) {
            return (T) new GroceryAddViewModel(groceriesRepository, new Grocery("Grocery"));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
