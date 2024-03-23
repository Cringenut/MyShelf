package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import lombok.Getter;

public class GroceryAddViewModel extends ViewModel {

    private final GroceriesRepository groceriesRepo;
    @Getter
    private final Grocery groceryToAdd;

    public GroceryAddViewModel(GroceriesRepository groceriesRepo, Grocery groceryToAdd) {
        this.groceriesRepo = groceriesRepo;
        this.groceryToAdd = groceryToAdd;
    }

    public void addGrocery() {
        groceriesRepo.addGrocery(groceryToAdd);
    }
}
