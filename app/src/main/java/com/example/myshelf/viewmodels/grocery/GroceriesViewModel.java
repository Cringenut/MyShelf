package com.example.myshelf.viewmodels.grocery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.util.List;

public class GroceriesViewModel extends ViewModel {
    private final GroceriesRepository groceriesRepo;
    private final LiveData<List<Grocery>> groceries;

    public GroceriesViewModel(GroceriesRepository groceriesRepo) {
        this.groceriesRepo = groceriesRepo;
        this.groceries = groceriesRepo.getGroceries();
    }
}
