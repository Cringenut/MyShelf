package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.time.LocalDate;

import lombok.Getter;

public class GroceryAddViewModel extends ViewModel {

    private final GroceriesRepository groceriesRepo;
    @Getter
    private final Grocery groceryToAdd;
    @Getter
    private final MutableLiveData<String> groceryName = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> groceryExpirationDate = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> groceryAdditionDate = new MutableLiveData<>();


    // Setting default values
    public GroceryAddViewModel(GroceriesRepository groceriesRepo, Grocery groceryToAdd) {
        this.groceriesRepo = groceriesRepo;
        this.groceryToAdd = groceryToAdd;
        this.groceryName.setValue(groceryToAdd.getGroceryName());
        this.groceryExpirationDate.setValue(groceryToAdd.getGroceryExpirationDate());
        this.groceryAdditionDate.setValue(LocalDate.now());
    }

    // Setters
    public void setGroceryName(String name) {
        groceryToAdd.setGroceryName(name);
        groceryName.setValue(name);
    }

    public void setGroceryExpirationDate(LocalDate date) {
        groceryToAdd.setGroceryExpirationDate(date);
        groceryExpirationDate.setValue(date);
    }

    public void setGroceryAdditionDate(LocalDate date) {
        groceryToAdd.setGroceryAdditionDate(date);
        groceryAdditionDate.setValue(date);
    }

    public void addGrocery() {
        groceriesRepo.addGrocery(groceryToAdd);
    }

    public void resetData() {
        this.groceryName.setValue(groceryToAdd.getGroceryName());
        this.groceryExpirationDate.setValue(groceryToAdd.getGroceryExpirationDate());
    }
}
