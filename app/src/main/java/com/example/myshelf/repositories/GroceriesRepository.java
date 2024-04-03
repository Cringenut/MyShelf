package com.example.myshelf.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

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

    public void addGrocery(Grocery grocery) {
        GroceryDatabase.databaseWriteExecutor.execute(() -> {
            groceryDAO.insert(grocery);
        });
    }

    public void deleteGrocery(Grocery grocery) {
        GroceryDatabase.databaseWriteExecutor.execute(() -> {
            groceryDAO.delete(grocery);
        });
    }

}
