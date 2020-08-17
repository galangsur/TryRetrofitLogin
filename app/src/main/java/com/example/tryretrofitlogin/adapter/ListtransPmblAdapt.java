package com.example.tryretrofitlogin.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListtransPmblAdapt extends RecyclerView.Adapter<ListtransPmblAdapt.TransPmblViewHolder> {


    @NonNull
    @Override
    public TransPmblViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TransPmblViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TransPmblViewHolder extends RecyclerView.ViewHolder {

        public TransPmblViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
