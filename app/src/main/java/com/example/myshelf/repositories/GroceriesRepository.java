package com.example.myshelf.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myshelf.databases.grocery.GroceryDAO;
import com.example.myshelf.databases.grocery.GroceryDatabase;
import com.example.myshelf.objects.Grocery;

import java.util.List;

public class GroceriesRepository {

    GroceryDatabase groceryDatabase;
    GroceryDAO groceryDAO;
    private MutableLiveData<List<Grocery>> groceriesList;

    public GroceriesRepository(Application application) {
        this.groceryDatabase = GroceryDatabase.getDatabase(application);;
        this.groceryDAO = groceryDatabase.groceryDAO();
        this.groceriesList.setValue(groceryDAO.getAll());
    }

    public LiveData<List<Grocery>> getGroceries() {
        return groceriesList;
    }

}
