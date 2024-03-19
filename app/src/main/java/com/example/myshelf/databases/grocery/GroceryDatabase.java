package com.example.myshelf.databases.grocery;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myshelf.objects.Grocery;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Grocery.class}, version = 2, exportSchema = false)
public abstract class GroceryDatabase extends RoomDatabase {
    public abstract GroceryDAO groceryDAO();
    private static volatile GroceryDatabase groceryDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static GroceryDatabase getDatabase(final Context context) {
        if (groceryDatabase == null) {
            synchronized (GroceryDatabase.class) {
                if (groceryDatabase == null) {
                    groceryDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    GroceryDatabase.class, "grocery_database")
                            .build();
                }
            }
        }
        return groceryDatabase;
    }
}
