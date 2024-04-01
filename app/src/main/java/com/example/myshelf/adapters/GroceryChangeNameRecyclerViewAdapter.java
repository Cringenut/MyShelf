package com.example.myshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshelf.databinding.ViewGroceryBinding;

import java.util.ArrayList;
import java.util.List;

public class GroceryChangeNameRecyclerViewAdapter
        extends RecyclerView.Adapter<GroceryChangeNameRecyclerViewAdapter.ViewHolder> {

    private List<String> groceryNamesList = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        ViewGroceryBinding binding = ViewGroceryBinding
                .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);

        return new GroceryChangeNameRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return groceryNamesList.size();
    }

    public void setGroceryNames(List<String> groceryNames) {
        this.groceryNamesList = groceryNames;
        notifyDataSetChanged(); // Notify any registered observers that the data set has changed.
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroceryBinding binding;
        public ViewHolder(ViewGroceryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
