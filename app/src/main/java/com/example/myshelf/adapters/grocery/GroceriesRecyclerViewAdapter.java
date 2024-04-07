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

    // Interfaces for click events
    public interface OnGroceryClickListener {
        void onGroceryClick(Grocery grocery);
    }
    public interface OnGroceryDeleteClickListener {
        void onGroceryDeleteClick(Grocery grocery);
    }

    private List<Grocery> groceriesList = new ArrayList<>();
    private Grocery selectedGrocery;
    private final OnGroceryClickListener listener;
    private final OnGroceryDeleteClickListener deleteListener;
    // Constructor updated to include the listener
    public GroceriesRecyclerViewAdapter(OnGroceryClickListener listener,
                                        OnGroceryDeleteClickListener deleteListener) {
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
        holder.bind(grocery, grocery.equals(selectedGrocery));

        holder.itemView.setOnClickListener(
                v -> listener.onGroceryClick(grocery));
        holder.binding.btnDelete.setOnClickListener(
                v -> deleteListener.onGroceryDeleteClick(grocery));
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

        public void bind(Grocery grocery, boolean isSelected) {
            binding.textGroceryName.setText(grocery.getGroceryName());

            if(grocery.getGroceryExpirationDate() != null) {
                binding.textGroceryExpirationDate.setVisibility(View.VISIBLE);
                binding.textGroceryExpirationDate.setText(
                        DateConverter
                        .dateToString(grocery
                        .getGroceryExpirationDate()));
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) binding
                                .textGroceryName
                                .getLayoutParams();
                params.bottomMargin = 56;
                binding.textGroceryName
                        .setLayoutParams(params);
            } else {
                binding.textGroceryExpirationDate.setVisibility(View.GONE);
            }

            if (isSelected) {
                binding.layoutDetails.setVisibility(View.VISIBLE);
            } else {
                binding.layoutDetails.setVisibility(View.GONE);
            }
        }
    }

}
