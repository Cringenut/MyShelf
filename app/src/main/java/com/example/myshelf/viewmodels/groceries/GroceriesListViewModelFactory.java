package com.example.myshelf.viewmodels.groceries;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myshelf.repositories.GroceriesRepository;

public class GroceriesListViewModelFactory implements ViewModelProvider.Factory {
    private final GroceriesRepository groceriesRepository;

    public GroceriesListViewModelFactory(Context context) {
        groceriesRepository = new GroceriesRepository(context);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GroceriesListViewModel.class)) {
            //noinspection unchecked
            return (T) new GroceriesListViewModel(groceriesRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
