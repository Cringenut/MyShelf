package com.example.myshelf.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myshelf.databinding.ActivityGroceriesBinding;

public class GroceriesActivity extends AppCompatActivity {

    // For now the only purpose is to get context and act as the main holder, other operations are held in fragments
    private ActivityGroceriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGroceriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}