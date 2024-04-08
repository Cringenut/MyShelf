package com.example.myshelf.adapters.grocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myshelf.R;

import java.time.LocalDate;
import java.util.List;

public class GroceryCalendarGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<LocalDate> dates;

    public GroceryCalendarGridViewAdapter(Context context, List<LocalDate> dates) {
        mContext = context;
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_calendar_cell, parent, false);
        }

        return convertView;
    }
}
