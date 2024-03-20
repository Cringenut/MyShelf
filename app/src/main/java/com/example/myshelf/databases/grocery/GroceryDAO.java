package com.example.myshelf.databases.grocery;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myshelf.objects.Grocery;

import java.util.List;

@Dao
public interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Grocery grocery);
    @Update
    void update(Grocery grocery);

    @Delete
    void delete(Grocery grocery);
    @Query("SELECT * FROM grocery")
    LiveData<List<Grocery>> getAll();

    @Query("SELECT * FROM grocery WHERE groceryId = :id")
    Grocery getById(int id);

}
