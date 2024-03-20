package com.example.myshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshelf.databinding.ViewRecyclerViewGroceryBinding;
import com.example.myshelf.objects.Grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceriesRecyclerViewAdapter
        extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.ViewHolder> {

    private List<Grocery> groceriesList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        ViewRecyclerViewGroceryBinding binding = ViewRecyclerViewGroceryBinding
                        .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.textGroceryName.setText(groceriesList.get(position).groceryName);
    }

    @Override
    public int getItemCount() {
        return groceriesList.size();
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceriesList = groceries;
        notifyDataSetChanged(); // Notify any registered observers that the data set has changed.
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewRecyclerViewGroceryBinding binding;
        public ViewHolder(ViewRecyclerViewGroceryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }




}
