package com.futureapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;


public class HourViewAdapter extends RecyclerView.Adapter<HourViewAdapter.MyViewHolder> {
    private ArrayList<HashMap<String, String>> hourValues;
    private Context mContext;

    public HourViewAdapter(Context mContext, ArrayList<HashMap<String, String>> hourValues) {
        this.hourValues = hourValues;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_view_card, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HashMap<String, String> temp = hourValues.get(position);
    }


    @Override
    public int getItemCount() {
        return hourValues.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hour;
        private TextView capacity;

        public MyViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            capacity = itemView.findViewById(R.id.capacity);

        }
    }
}
