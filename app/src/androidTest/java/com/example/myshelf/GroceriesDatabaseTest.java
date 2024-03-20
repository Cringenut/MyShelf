package com.example.myshelf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myshelf.databases.grocery.GroceryDAO;
import com.example.myshelf.databases.grocery.GroceryDatabase;
import com.example.myshelf.objects.Grocery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GroceriesDatabaseTest {

    private GroceryDatabase db;
    private GroceryDAO groceryDAO;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, GroceryDatabase.class).build();
        groceryDAO = db.groceryDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writeAndReadGrocery() {
        Grocery grocery = new Grocery("Grocery");
        groceryDAO.insert(grocery);

        List<Grocery> allGroceries = groceryDAO.getAll().getValue();
        System.out.println(allGroceries);
    }

    @Test
    public void deleteGrocery() {
        Grocery grocery = new Grocery("Grocery");

        groceryDAO.insert(grocery);
        System.out.println("Database grocery inserted: " + groceryDAO.getAll().getValue().get(0));

        groceryDAO.delete(grocery);
        Grocery byId = groceryDAO.getById(grocery.getGroceryId());
        if (byId == null) {
            System.out.println("Database grocery successfully deleted");
        } else {
            System.out.println("Database grocery still exists");
        }

        assertNull(byId);
    }

    @Test
    public void insertAndRetrieveGroceries() throws Exception {
        Grocery apple = new Grocery("Apples");
        Grocery bread = new Grocery("Bread");
        Grocery milk = new Grocery("Milk");

        groceryDAO.insert(apple);
        groceryDAO.insert(bread);
        groceryDAO.insert(milk);

        List<Grocery> allGroceries = groceryDAO.getAll().getValue();
        assertEquals(allGroceries.size(), 3);
    }

}
