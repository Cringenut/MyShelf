package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myshelf.adapters.grocery.GroceryCalendarGridViewAdapter;
import com.example.myshelf.databinding.FragmentGroceryCalendarBinding;
import com.example.myshelf.viewmodels.groceries.GroceryCalendarViewModel;

import java.time.LocalDate;

public class GroceryCalendarFragment extends Fragment {

    FragmentGroceryCalendarBinding binding;
    GroceryCalendarViewModel calendarViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        calendarViewModel = new ViewModelProvider(this)
                .get(GroceryCalendarViewModel.class);
        calendarViewModel.setCurrentMonth(LocalDate.now().getMonth().plus(2));
        calendarViewModel.setCurrentYear(LocalDate.now().getYear());
        binding.calendarGrid
                .setAdapter(new GroceryCalendarGridViewAdapter(calendarViewModel
                        .getDaysOfMonth()));
    }


}
