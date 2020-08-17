package com.example.tryretrofitlogin.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListleltransPsrtAdapt extends RecyclerView.Adapter<ListleltransPsrtAdapt.LeltransPsrtViewHolder> {


    @NonNull
    @Override
    public LeltransPsrtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LeltransPsrtViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LeltransPsrtViewHolder extends RecyclerView.ViewHolder {
        public LeltransPsrtViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
