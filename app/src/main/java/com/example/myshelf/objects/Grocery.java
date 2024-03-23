package com.example.myshelf.objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grocery {

    public Grocery(@NonNull String groceryName) {
        this.groceryName = groceryName;
    }

    @PrimaryKey(autoGenerate = true)
    private int groceryId;

    @NonNull
    @ColumnInfo(name="grocery_name")
    private String groceryName;

}
