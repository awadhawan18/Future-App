package com.futureapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> {
    private ArrayList<PlantTab.Plant> plants;
    Context mContext;

    PlantAdapter(Context mContext, ArrayList<PlantTab.Plant> plants) {
        this.plants = plants;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plants_list, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlantTab.Plant plant = plants.get(position);
        holder.name.setText(plant.name);
        holder.capacity.setText(plant.capacity);

        //holder.plant_view.setBackgroundColor(Integer.parseInt("ffff00", 16));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView capacity;
        private LinearLayout plant_view;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.plant_name_text_view);
            capacity = itemView.findViewById(R.id.plant_capacity_text_view);
            plant_view = itemView.findViewById(R.id.plant_view);
        }
    }
}
