package com.example.myshelf.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grocery {
    @PrimaryKey
    private int groceryId;
}
