package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.GroupchatActivity;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyuser.SuccessItem;

import java.util.ArrayList;

public class ListlelasPelelangAdapt extends RecyclerView.Adapter<ListlelasPelelangAdapt.LelAsPelelangViewHolder> {
    ArrayList<SuccessItem> getlelbrjalan;
    Context lelbrjalanContex;

    public ListlelasPelelangAdapt(Context lelbrjalanContex, ArrayList<SuccessItem> getlelbrjalan ){
        super();
        this.lelbrjalanContex = lelbrjalanContex;
        this.getlelbrjalan = getlelbrjalan;
    }


    @NonNull
    @Override
    public ListlelasPelelangAdapt.LelAsPelelangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listlelaspelelangitem_layout,parent,false);
        return new LelAsPelelangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListlelasPelelangAdapt.LelAsPelelangViewHolder holder, final int position) {
        holder.lelaspelelang_id.setText(getlelbrjalan.get(position).getId());
        holder.gchatToken.setText(getlelbrjalan.get(position).getGchatId());

        holder.btn_tolelroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lelbrjalanContex, "sds" + getlelbrjalan.get(position).getGchatId(), Toast.LENGTH_SHORT).show();
                Intent lelaspelelang = new Intent(lelbrjalanContex, GroupchatActivity.class);
                lelaspelelang.putExtra("gchattoken",getlelbrjalan.get(position).getGchatId());
                lelbrjalanContex.startActivity(lelaspelelang);
            }
        });


    }

    @Override
    public int getItemCount() {
        return getlelbrjalan.size();
    }

    public class LelAsPelelangViewHolder extends RecyclerView.ViewHolder {
        TextView lelaspelelang_id,gchatToken;
        ImageView btn_tolelroom;

        public LelAsPelelangViewHolder(@NonNull View itemView) {
            super(itemView);
            lelaspelelang_id = itemView.findViewById(R.id.idlelbrjalan);
            gchatToken = itemView.findViewById(R.id.gchattoken_key);
            btn_tolelroom = itemView.findViewById(R.id.btn_toroomaspelelang);
        }
    }
}
