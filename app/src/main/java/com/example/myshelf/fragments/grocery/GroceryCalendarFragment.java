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
import com.example.myshelf.databases.grocery.DateConverter;
import com.example.myshelf.databinding.FragmentGroceryCalendarBinding;
import com.example.myshelf.viewmodels.groceries.GroceriesRepositoryViewModelFactory;
import com.example.myshelf.viewmodels.groceries.GroceryAddViewModel;
import com.example.myshelf.viewmodels.groceries.GroceryCalendarViewModel;

import java.time.LocalDate;

public class GroceryCalendarFragment extends Fragment implements GroceryCalendarGridViewAdapter.OnCalendarClickListener {

    private FragmentGroceryCalendarBinding binding;
    private GroceryCalendarViewModel calendarViewModel;
    private GroceryCalendarGridViewAdapter adapter;
    private GroceryAddViewModel viewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext(), LocalDate.now());  // Assuming you now pass the required LocalDate here
        calendarViewModel = new ViewModelProvider(this, factory).get(GroceryCalendarViewModel.class);
        viewModel = new ViewModelProvider(requireActivity(), factory).get(GroceryAddViewModel.class);
        adapter = new GroceryCalendarGridViewAdapter(this);

        // Set the initial date and update days of the month
        LocalDate initialDate = LocalDate.now();  // This should be dynamically determined as needed
        calendarViewModel.setInitialDate(initialDate);  // Assuming such a setter exists to handle changes
        calendarViewModel.setDaysOfMonth();  // Trigger the update of days

        // Observe changes in days of month
        calendarViewModel.getDaysOfMonth().observe(getViewLifecycleOwner(), newDays -> {
            adapter.setDaysOfMonth(newDays);
            binding.calendarGrid.setAdapter(adapter);  // Set or update adapter here if necessary
        });

        calendarViewModel.getSelectedDate().observe(getViewLifecycleOwner(), selectedDay -> {
            adapter.setSelectedDate(calendarViewModel.getSelectedDate().getValue());
            binding.textSelectedDate.setText(DateConverter.dateToString(selectedDay));
        });

        binding.btnConfirm.setOnClickListener(v -> {
            viewModel.setGroceryExpirationDate(calendarViewModel.getSelectedDate().getValue());
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        binding.btnAdd.setOnClickListener(v -> calendarViewModel.addMonth());
        binding.btnSubtract.setOnClickListener(v -> calendarViewModel.subtractMonth());

    }


    @Override
    public void onCalendarClick(LocalDate date) {
        calendarViewModel.setSelectedDate(date);
    }
}
