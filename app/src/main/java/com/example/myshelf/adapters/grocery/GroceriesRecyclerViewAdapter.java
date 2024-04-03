package com.example.myshelf.adapters.grocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshelf.databases.grocery.DateConverter;
import com.example.myshelf.databinding.ViewGroceryBinding;
import com.example.myshelf.objects.Grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceriesRecyclerViewAdapter
        extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.ViewHolder> {

    private List<Grocery> groceriesList = new ArrayList<>();

    // Interface for click events
    public interface OnGroceryClickListener {
        void onGroceryClick(Grocery grocery);
    }

    private final OnGroceryClickListener listener;
    // Constructor updated to include the listener
    public GroceriesRecyclerViewAdapter(OnGroceryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        ViewGroceryBinding binding = ViewGroceryBinding
                        .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    // Creating RecyclerView element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grocery grocery = groceriesList.get(position);
        holder.binding.textGroceryName.setText(grocery.getGroceryName());
        // If no expiration date is set hide the text
        if (grocery.getGroceryExpirationDate() == null) {
            holder.binding.textGroceryExpirationDate.setVisibility(View.INVISIBLE);
        } else {
            holder.binding.textGroceryExpirationDate.setText(DateConverter.dateToString(grocery.getGroceryExpirationDate()));
        }

        holder.itemView.setOnClickListener(v -> listener.onGroceryClick(grocery));
    }

    @Override
    public int getItemCount() {
        return groceriesList.size();
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceriesList = groceries;
        notifyDataSetChanged(); // Notify any registered observers that the data set has changed.
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        // Set binding
        ViewGroceryBinding binding;
        public ViewHolder(ViewGroceryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
