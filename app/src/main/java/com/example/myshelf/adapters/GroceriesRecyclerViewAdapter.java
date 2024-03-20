package com.example.myshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshelf.R;
import com.example.myshelf.objects.Grocery;

import java.util.List;

public class GroceriesRecyclerViewAdapter
        extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.ViewHolder> {

    private List<Grocery> groceryList;

    public GroceriesRecyclerViewAdapter(List<Grocery> groceryList) {
        this.groceryList = groceryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groceryView = inflater.inflate(R.layout.view_recycler_view_grocery, parent, false);

        ViewHolder viewHolder = new ViewHolder(groceryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
        //return groceryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }




}
