package com.futureapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import top.defaults.drawabletoolbox.DrawableBuilder;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> {
    private ArrayList<PlantTab.Plant> plants;
    private Context mContext;

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
        holder.total.setText(String.valueOf(plant.total));
        holder.renewable.setText(String.valueOf(plant.renewable));
        holder.nonrenewable.setText(String.valueOf(plant.nonrenewable));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                236, (float) plant.renewable/plant.total);
        holder.image_renewable.setLayoutParams(params);
        holder.image_renewable.setImageResource(R.drawable.bg_renewable);

        params = new LinearLayout.LayoutParams(0,
                236, (float) plant.nonrenewable/plant.total);
        holder.image_nonrenewable.setLayoutParams(params);
        holder.image_nonrenewable.setImageResource(R.drawable.bg_nonrenewable);
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        TextView total, renewable, nonrenewable;
        LinearLayout plantView, background_layout;
        MaterialCardView plantCard;
        ImageView image_renewable, image_nonrenewable;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.plant_name_text_view);
            total = itemView.findViewById(R.id.plant_capacity_text_view);
            renewable = itemView.findViewById(R.id.plant_renewable);
            nonrenewable = itemView.findViewById(R.id.plant_nonrenewable);
            plantView = itemView.findViewById(R.id.plant_view);
            plantCard = itemView.findViewById(R.id.plant_card);
            image_renewable = itemView.findViewById(R.id.image_renewable);
            image_nonrenewable = itemView.findViewById(R.id.image_nonrenewable);
            background_layout = itemView.findViewById(R.id.background_layout);
        }
    }
}
