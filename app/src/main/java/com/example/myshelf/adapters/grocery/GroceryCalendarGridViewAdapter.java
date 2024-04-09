package com.example.myshelf.adapters.grocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myshelf.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroceryCalendarGridViewAdapter extends BaseAdapter {
    private List<LocalDate> daysOfMonth = new ArrayList<>();

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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_calendar_cell, parent, false);
        }
        if (daysOfMonth.isEmpty()) {
            return convertView;
        }

        int firstIndex = daysOfMonth.get(0).getDayOfWeek().getValue() - 1;
        if (position >=  firstIndex && position < firstIndex + daysOfMonth.size()) {
            TextView textView = convertView.findViewById(R.id.text_date);
            textView.setText(String.valueOf(daysOfMonth
                    .get(position - firstIndex)
                    .getDayOfMonth()));
        }

        return convertView;
    }
}
