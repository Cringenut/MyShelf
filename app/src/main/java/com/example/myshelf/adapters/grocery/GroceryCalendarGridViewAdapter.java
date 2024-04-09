package com.example.myshelf.adapters.grocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myshelf.databinding.ViewCalendarCellBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroceryCalendarGridViewAdapter extends BaseAdapter {
    private List<LocalDate> daysOfMonth = new ArrayList<>();
    private LocalDate selectedDate;
    private final OnCalendarClickListener calendarClickListener; // Member to hold the calendarClickListener instance

    public GroceryCalendarGridViewAdapter(OnCalendarClickListener calendarClickListener) {
        this.calendarClickListener = calendarClickListener;
    }

    public void setSelectedDate(LocalDate date) {
        selectedDate = date;
        notifyDataSetChanged();
    }


    // Interfaces for click events
    public interface OnCalendarClickListener {
        void onCalendarClick(LocalDate date);
    }


    public void setDaysOfMonth(List<LocalDate> daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return daysOfMonth.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        CalendarViewHolder holder;
        ViewCalendarCellBinding binding;

        if (convertView == null) {
            binding = ViewCalendarCellBinding.inflate(LayoutInflater.from(context), parent, false);
            convertView = binding.getRoot();
            holder = new CalendarViewHolder(binding);
            convertView.setTag(holder);
        } else {
            holder = (CalendarViewHolder) convertView.getTag();
        }

        int firstIndex = daysOfMonth.get(0).getDayOfWeek().getValue() - 1;
        if (position >=  firstIndex && position < firstIndex + daysOfMonth.size()) {
            LocalDate date = daysOfMonth.get(position - firstIndex);
            boolean isSelected = date.equals(selectedDate);
            holder.bind(date, isSelected);
        } else {
            holder.bind(null, false);
        }


        holder.binding.getRoot().setOnClickListener(
                v -> calendarClickListener.onCalendarClick(holder.date));

        return convertView;
    }

    protected static class CalendarViewHolder {
        ViewCalendarCellBinding binding;
        LocalDate date;

        public CalendarViewHolder(ViewCalendarCellBinding binding) {
            this.binding = binding;
        }

        public void bind(LocalDate date, boolean isSelected) {
            if (date == null) {
                binding.textDate.setVisibility(View.INVISIBLE);
                binding.layoutSelected.setVisibility(View.INVISIBLE);
                return;
            }

            this.date = date;
            binding.textDate.setVisibility(View.VISIBLE);
            binding.textDate.setText(String.valueOf(date.getDayOfMonth()));
            if (isSelected) {
                binding.layoutSelected.setVisibility(View.VISIBLE);
            } else {
                binding.layoutSelected.setVisibility(View.INVISIBLE);
            }
        }
    }
}
