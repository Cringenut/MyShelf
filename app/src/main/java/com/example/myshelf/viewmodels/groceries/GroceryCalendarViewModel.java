package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class GroceryCalendarViewModel extends ViewModel {

    @Getter
    private final MutableLiveData<List<LocalDate>> daysOfMonth = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> initialDate = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<LocalDate> selectedDate = new MutableLiveData<>();

    public GroceryCalendarViewModel(LocalDate initialDate) {
        this.initialDate.setValue(initialDate);
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate.setValue(initialDate);
    }

    public void addMonth() {
        initialDate.setValue(initialDate.getValue().plusMonths(1));
        setDaysOfMonth();
    }

    public void setSelectedDate(LocalDate date) {
        selectedDate.setValue(date);
    }

    public void subtractMonth() {
        initialDate.setValue(initialDate.getValue().plusMonths(-1));
        setDaysOfMonth();
    }

    public void setDaysOfMonth() {
        List<LocalDate> daysOfMonth = new ArrayList<>();

        // Create a LocalDate object for the first day of the given month and year
        LocalDate start = LocalDate.of(initialDate.getValue().getYear(),
                initialDate.getValue().getMonth(),
                1);

        // Get the last day of the month
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Loop through the month and collect the DayOfWeek for each date
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            daysOfMonth.add(date);
        }

        this.daysOfMonth.setValue(daysOfMonth);
    }

}
