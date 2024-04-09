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
    private GroceryAddViewModel mainViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroceryCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GroceriesRepositoryViewModelFactory factory = new GroceriesRepositoryViewModelFactory(getContext(), LocalDate.now());  // Assuming you now pass the required LocalDate here
        calendarViewModel = new ViewModelProvider(this, factory).get(GroceryCalendarViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity(), factory).get(GroceryAddViewModel.class);
        adapter = new GroceryCalendarGridViewAdapter(this);

        if (mainViewModel.getGroceryExpirationDate().getValue() == null) {
            calendarViewModel.setInitialDate(LocalDate.now());  // Assuming such a setter exists to handle changes
        } else {
            calendarViewModel.setInitialDate(mainViewModel.getGroceryExpirationDate().getValue());  // Assuming such a setter exists to handle changes
            calendarViewModel.setSelectedDate(mainViewModel.getGroceryExpirationDate().getValue());
        }
        calendarViewModel.setDaysOfMonth();  // Trigger the update of days
        binding.calendarGrid.setAdapter(adapter);

        // Observe changes in days of month
        calendarViewModel.getDaysOfMonth().observe(getViewLifecycleOwner(), daysOfMonth -> {
            adapter.setDaysOfMonth(daysOfMonth);
            String currentMonthAndYear = daysOfMonth.get(0).getMonth() + ", " + daysOfMonth.get(0).getYear();
            binding.textCurrentMonthAndYear.setText(currentMonthAndYear);
        });

        calendarViewModel.getSelectedDate().observe(getViewLifecycleOwner(), selectedDate -> {
            adapter.setSelectedDate(selectedDate);
            binding.textSelectedDate.setText(DateConverter.dateToString(selectedDate));
        });

        calendarViewModel.getInitialDate().observe(getViewLifecycleOwner(), initialDate ->
                adapter.setDaysOfMonth(calendarViewModel.getDaysOfMonth().getValue()));

        binding.btnConfirm.setOnClickListener(v -> {
            mainViewModel.setGroceryExpirationDate(calendarViewModel.getSelectedDate().getValue());
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarViewModel.setSelectedDate(null);
                calendarViewModel.setInitialDate(LocalDate.now());
                calendarViewModel.setDaysOfMonth();
            }
        });

        binding.btnAdd.setOnClickListener(v -> calendarViewModel.addMonth());
        binding.btnSubtract.setOnClickListener(v -> calendarViewModel.subtractMonth());

    }

    @Override
    public void onCalendarClick(LocalDate date) {
        calendarViewModel.setSelectedDate(date);
    }
}
