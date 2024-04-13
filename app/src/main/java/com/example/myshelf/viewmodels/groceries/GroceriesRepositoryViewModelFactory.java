package com.example.myshelf.viewmodels.groceries;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.time.LocalDate;

public class GroceriesRepositoryViewModelFactory implements ViewModelProvider.Factory {
    private final GroceriesRepository groceriesRepository;
    private LocalDate initialDate;

    // Encapsulating Repository creation
    public GroceriesRepositoryViewModelFactory(Context context) {
        groceriesRepository = new GroceriesRepository(context);
    }

    public GroceriesRepositoryViewModelFactory(Context context, LocalDate initialDate) {
        groceriesRepository = new GroceriesRepository(context);
        this.initialDate = initialDate; // Initialize the date
    }

    // Pass arguments to ViewModel
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GroceriesListViewModel.class)) {
            return (T) new GroceriesListViewModel(groceriesRepository);
        } else if (modelClass.isAssignableFrom(GroceryManipulationViewModel.class)) {
            return (T) new GroceryManipulationViewModel(groceriesRepository, new Grocery("Grocery"));
        } else if (modelClass.isAssignableFrom(GroceryCalendarViewModel.class)) {
            return (T) new GroceryCalendarViewModel(initialDate);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
