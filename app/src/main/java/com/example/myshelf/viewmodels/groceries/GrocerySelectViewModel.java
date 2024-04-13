package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.objects.Grocery;


public class GrocerySelectViewModel extends ViewModel {

    private final MutableLiveData<Grocery> selectedGrocery = new MutableLiveData<>();

    public void select(Grocery grocery) {
        selectedGrocery.setValue(grocery);
    }

    public LiveData<Grocery> getSelectedGrocery() {
        return selectedGrocery;
    }

}
