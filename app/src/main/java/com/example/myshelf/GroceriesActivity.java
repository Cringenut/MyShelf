package com.example.myshelf;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myshelf.databinding.ActivityGroceriesBinding;

public class GroceriesActivity extends AppCompatActivity {

    private ActivityGroceriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGroceriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}