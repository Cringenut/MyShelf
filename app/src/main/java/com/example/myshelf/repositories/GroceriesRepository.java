package com.example.myshelf.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myshelf.databases.grocery.GroceryDAO;
import com.example.myshelf.databases.grocery.GroceryDatabase;
import com.example.myshelf.objects.Grocery;

import java.util.List;

public class GroceriesRepository {
    GroceryDAO groceryDAO;

    public GroceriesRepository(Context context) {
        GroceryDatabase db = GroceryDatabase.getDatabase(context.getApplicationContext());
        this.groceryDAO = db.groceryDAO();
    }

    public LiveData<List<Grocery>> getGroceries() {
        return groceryDAO.getAll();
    }

}
