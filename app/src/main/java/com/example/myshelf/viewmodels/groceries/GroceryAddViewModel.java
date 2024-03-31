package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import lombok.Getter;

public class GroceryAddViewModel extends ViewModel {

    private final GroceriesRepository groceriesRepo;
    @Getter
    private final Grocery groceryToAdd;
    private MutableLiveData<String> groceryName = new MutableLiveData<>();

    public GroceryAddViewModel(GroceriesRepository groceriesRepo, Grocery groceryToAdd) {
        this.groceriesRepo = groceriesRepo;
        this.groceryToAdd = groceryToAdd;
        this.groceryName.setValue(groceryToAdd.getGroceryName());
    }

    public LiveData<String> getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String name) {
        groceryToAdd.setGroceryName(name);
        groceryName.setValue(name);
    }

    public void addGrocery() {
        groceriesRepo.addGrocery(groceryToAdd);
    }
}
