package com.example.myshelf.adapters.grocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private Grocery selectedGrocery;


    // Interfaces for click events
    public interface OnGroceryClickListener {
        void onGroceryClick(Grocery grocery);
    }
    public interface OnGroceryDeleteClickListener {
        void onGroceryDeleteClick(Grocery grocery);
    }

    private final OnGroceryClickListener listener;
    private final OnGroceryDeleteClickListener deleteListener;
    // Constructor updated to include the listener
    public GroceriesRecyclerViewAdapter(OnGroceryClickListener listener, OnGroceryDeleteClickListener deleteListener) {
        this.listener = listener;
        this.deleteListener = deleteListener;
    }


    public void setSelectedGrocery(Grocery grocery) {
        if (selectedGrocery != null) {
            notifyItemChanged(groceriesList.indexOf(selectedGrocery));
        }
        this.selectedGrocery = grocery;
        notifyItemChanged(groceriesList.indexOf(selectedGrocery));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        // Inflate the custom layout
        ViewGroceryBinding binding = ViewGroceryBinding
                .inflate(LayoutInflater
                        .from(context), parent, false);

        return new ViewHolder(binding);
    }

    // Creating RecyclerView element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grocery grocery = groceriesList.get(position);
        holder.binding.textGroceryName.setText(grocery.getGroceryName());
        // If no expiration date is set hide the text
        if (grocery.getGroceryExpirationDate() == null) {
            holder.binding.textGroceryExpirationDate.setVisibility(View.GONE);
        } else {
            holder.binding.textGroceryExpirationDate.setText(DateConverter.dateToString(grocery.getGroceryExpirationDate()));
            TextView text = holder.binding.textGroceryName;
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
            params.bottomMargin = 48;
            text.setLayoutParams(params);
        }

        if (grocery.equals(selectedGrocery)) {
            holder.binding.layoutDetails.setVisibility(View.VISIBLE);
        } else {
            holder.binding.layoutDetails.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> listener.onGroceryClick(grocery));
        holder.binding.btnDelete.setOnClickListener(v -> deleteListener.onGroceryDeleteClick(grocery));
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
