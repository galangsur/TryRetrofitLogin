package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailLelangActivity;
import com.example.tryretrofitlogin.activity.ListLelangActivity;
import com.example.tryretrofitlogin.responses.getlelang.SuccessItem;

import java.util.ArrayList;

public class RVadapter extends RecyclerView.Adapter<RVadapter.MyViewHolder> {

    ArrayList<SuccessItem> result;
    Context context;

    public RVadapter(Context context, ArrayList<SuccessItem> result) {
        super();
        this.result = result;
        this.context = context;
    }

    @Override
    public RVadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lelangitem_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.comment.setText(result.get(position).getComment());
        holder.harga.setText(result.get(position).getHarga());

        holder.toDaftarform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "clickon: " + result.get(position).getId());
                Intent adaptintent = new Intent(context, DetailLelangActivity.class);
                adaptintent.putExtra("lelid",result.get(position).getId());
                context.startActivity(adaptintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comment, harga, id;
        Button toDaftarform;

        public MyViewHolder(View view) {
            super(view);

            comment = view.findViewById(R.id.lelcomment);
            harga = view.findViewById(R.id.lelharga);
            toDaftarform = view.findViewById(R.id.btndftarform);
        }
    }
}
