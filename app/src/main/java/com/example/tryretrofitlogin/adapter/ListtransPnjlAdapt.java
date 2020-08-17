package com.example.tryretrofitlogin.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListtransPnjlAdapt extends RecyclerView.Adapter<ListtransPnjlAdapt.TransPnjlViewHolder> {


    @NonNull
    @Override
    public TransPnjlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TransPnjlViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TransPnjlViewHolder extends RecyclerView.ViewHolder {

        public TransPnjlViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
