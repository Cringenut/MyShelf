package com.example.myshelf.grocery;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Entity;

import java.time.LocalDate;

public class Grocery implements GroceryInterface {

    protected MutableLiveData<String> name;
    protected MutableLiveData<LocalDate> expirationDate;


    @Override
    public void SetName(String name) {
        this.name.setValue(name);
    }

    @Override
    public String GetName() {
        return this.name.getValue();
    }

    @Override
    public void SetExpirationDate(LocalDate expirationDate) {
        this.expirationDate.setValue(expirationDate);
    }

    @Override
    public LocalDate GetExpirationDate() {
        return this.expirationDate.getValue();
    }
}
