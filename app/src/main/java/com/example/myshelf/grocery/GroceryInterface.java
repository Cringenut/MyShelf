package com.example.myshelf.grocery;

import java.time.LocalDate;

public interface GroceryInterface {

    // Grocery name
    void SetName(String name);
    String GetName();

    // Grocery expiration date
    void SetExpirationDate(LocalDate expirationDate);
    LocalDate GetExpirationDate();


}
