package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class GroceryManipulationViewModel extends ViewModel {

    private final GroceriesRepository groceriesRepo;
    @Getter
    @Setter
    private Grocery grocery;
    @Getter
    private final MutableLiveData<String> groceryName = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> groceryExpirationDate = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> groceryAdditionDate = new MutableLiveData<>();


    // Setting default values
    public GroceryManipulationViewModel(GroceriesRepository groceriesRepo, Grocery grocery) {
        this.groceriesRepo = groceriesRepo;
        this.grocery = grocery;
        this.groceryName.setValue(grocery.getGroceryName());
        this.groceryExpirationDate.setValue(grocery.getGroceryExpirationDate());
        this.groceryAdditionDate.setValue(grocery.getGroceryAdditionDate());
    }

    // Setters
    public void setGroceryName(String name) {
        grocery.setGroceryName(name);
        groceryName.setValue(name);
    }

    public void setGroceryExpirationDate(LocalDate date) {
        grocery.setGroceryExpirationDate(date);
        groceryExpirationDate.setValue(date);
    }

    public void setGroceryAdditionDate(LocalDate date) {
        grocery.setGroceryAdditionDate(date);
        groceryAdditionDate.setValue(date);
    }

    public void addGrocery() {
        groceriesRepo.addGrocery(grocery);
    }

    public void editGrocery() {
        groceriesRepo.editGrocery(grocery);
    }

}
