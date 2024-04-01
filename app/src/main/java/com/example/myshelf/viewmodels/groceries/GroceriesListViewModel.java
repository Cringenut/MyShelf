package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.util.List;

import lombok.Getter;

// For now no manipulations with Grocery inside
// Later Grocery removal and editing would be implemented
public class GroceriesListViewModel extends ViewModel {
    private final GroceriesRepository groceriesRepo;
    @Getter
    private final LiveData<List<Grocery>> groceries;

    public GroceriesListViewModel(GroceriesRepository groceriesRepo) {
        this.groceriesRepo = groceriesRepo;
        this.groceries = groceriesRepo.getGroceries();
    }

}
