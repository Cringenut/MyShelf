package com.example.myshelf.viewmodels.groceries;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.util.List;

import lombok.Getter;

public class GroceriesListViewModel extends ViewModel {
    private final GroceriesRepository groceriesRepo;
    @Getter
    private final LiveData<List<Grocery>> groceries;

    public GroceriesListViewModel(GroceriesRepository groceriesRepo) {
        this.groceriesRepo = groceriesRepo;
        this.groceries = groceriesRepo.getGroceries();
    }

}
