package com.example.myshelf.objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

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
        this.groceryAdditionDate = LocalDate.now();
    }

    // Id
    @PrimaryKey(autoGenerate = true)
    private int groceryId;

    // Name
    @NonNull
    @ColumnInfo(name="grocery_name")
    private String groceryName;

    // Expiration date
    @ColumnInfo(name="grocery_expiration_date")
    private LocalDate groceryExpirationDate;

    // Date when grocery was added to "fridge"
    @NonNull
    @ColumnInfo(name="grocery_addition_date")
    private LocalDate groceryAdditionDate;


}
