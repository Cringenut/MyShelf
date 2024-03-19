package com.example.myshelf.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Grocery {
    @PrimaryKey
    private int groceryId;
}
