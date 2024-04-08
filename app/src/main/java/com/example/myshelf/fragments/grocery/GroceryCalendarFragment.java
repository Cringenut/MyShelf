package com.example.myshelf.fragments.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myshelf.adapters.grocery.GroceryCalendarGridViewAdapter;
import com.example.myshelf.databinding.FragmentGroceryCalendarBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroceryCalendarFragment extends Fragment {

    FragmentGroceryCalendarBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LocalDate date = LocalDate.now().withDayOfMonth(1);

        List<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        dates.add(date);
        dates.add(date);


        binding.calendarGrid.setAdapter(new GroceryCalendarGridViewAdapter(getContext(), dates));
    }


}
