package com.example.myshelf.viewmodels.groceries;

import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class GroceryCalendarViewModel extends ViewModel {

    @Setter
    private Month currentMonth;
    @Setter
    private int currentYear;

    public List<LocalDate> getDaysOfMonth() {
        List<LocalDate> daysOfMonth = new ArrayList<>();

        // Create a LocalDate object for the first day of the given month and year
        LocalDate start = LocalDate.of(currentYear, currentMonth.getValue(), 1);

        // Get the last day of the month
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Loop through the month and collect the DayOfWeek for each date
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            daysOfMonth.add(date);
        }

        return daysOfMonth;
    }

}
