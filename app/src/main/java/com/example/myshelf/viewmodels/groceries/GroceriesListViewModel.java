package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myshelf.adapters.grocery.GroceriesRecyclerViewAdapter;
import com.example.myshelf.objects.Grocery;
import com.example.myshelf.repositories.GroceriesRepository;

import java.util.List;

import lombok.Getter;

// For now no manipulations with Grocery inside
// Later Grocery removal and editing would be implemented
public class GroceriesListViewModel extends ViewModel implements GroceriesRecyclerViewAdapter.OnGroceryDeleteClickListener {
    private final GroceriesRepository groceriesRepo;
    @Getter
    private final LiveData<List<Grocery>> groceries;
    @Getter
    private final MutableLiveData<Grocery> selectedGrocery = new MutableLiveData<>();


    public GroceriesListViewModel(GroceriesRepository groceriesRepo) {
        this.groceriesRepo = groceriesRepo;
        this.groceries = groceriesRepo.getGroceries();
    }

    public void selectGrocery(Grocery grocery) {
        if (grocery.equals(selectedGrocery.getValue())) {
            // If the same item is clicked again, deselect it
            selectedGrocery.setValue(null);
        } else {
            selectedGrocery.setValue(grocery);
        }
    }

    @Override
    public void onGroceryDeleteClick(Grocery grocery) {
        groceriesRepo.deleteGrocery(grocery);
    }
}
