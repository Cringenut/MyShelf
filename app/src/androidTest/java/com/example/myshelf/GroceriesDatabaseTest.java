package com.example.myshelf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.os.Looper;
import android.os.Handler;

import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myshelf.databases.grocery.GroceryDAO;
import com.example.myshelf.databases.grocery.GroceryDatabase;
import com.example.myshelf.objects.Grocery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
    public void writeAndReadGrocery() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Grocery grocery = new Grocery("Grocery");
        groceryDAO.insert(grocery);

        new Handler(Looper.getMainLooper()).post(() -> {
            // This observer is now posted to run on the main thread.
            groceryDAO.getAll().observeForever(new Observer<List<Grocery>>() {
                @Override
                public void onChanged(List<Grocery> groceries) {
                    assertNotNull(groceries);
                    assertFalse(groceries.isEmpty());
                    latch.countDown(); // Notify the count down latch to release the wait
                }
            });
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS)); // Wait for LiveData to emit
    }

    @Test
    public void deleteGrocery() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Grocery grocery = new Grocery("Grocery");

        groceryDAO.insert(grocery);

        new Handler(Looper.getMainLooper()).post(() -> {
            groceryDAO.getAll().observeForever(new Observer<List<Grocery>>() {
                @Override
                public void onChanged(List<Grocery> groceries) {
                    if (!groceries.isEmpty()) {
                        System.out.println("Database grocery inserted: " + groceries.get(0));
                        groceryDAO.delete(grocery);

                        Grocery byId = groceryDAO.getById(grocery.getGroceryId());
                        if (byId == null) {
                            System.out.println("Database grocery successfully deleted");
                        } else {
                            System.out.println("Database grocery still exists");
                        }
                        assertNull(byId);
                        latch.countDown();
                    }
                }
            });
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }

    @Test
    public void insertAndRetrieveGroceries() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Grocery apple = new Grocery("Apples");
        Grocery bread = new Grocery("Bread");
        Grocery milk = new Grocery("Milk");

        groceryDAO.insert(apple);
        groceryDAO.insert(bread);
        groceryDAO.insert(milk);

        new Handler(Looper.getMainLooper()).post(() -> {
            groceryDAO.getAll().observeForever(new Observer<List<Grocery>>() {
                @Override
                public void onChanged(List<Grocery> groceries) {
                    if (groceries != null && groceries.size() == 3) {
                        assertEquals(3, groceries.size());
                        latch.countDown();
                    }
                }
            });
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }
}
