package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailLelangActivity;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.SuccessItem;

import java.util.ArrayList;

public class Lelbyhwsapiadapt extends RecyclerView.Adapter<Lelbyhwsapiadapt.LelSapiViewHolder> {
    ArrayList<SuccessItem> getlelbyhewanresult;
    Context sapicontext;

    public Lelbyhwsapiadapt(Context hewancontext, ArrayList<SuccessItem> getlelbyhewanresult){
        super();
        this.sapicontext = hewancontext;
        this.getlelbyhewanresult = getlelbyhewanresult;
    }

    @NonNull
    @Override
    public LelSapiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lelbyhewansapi_layout,parent,false);
        return new LelSapiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelSapiViewHolder holder, final int position) {
        holder.lelsapiid.setText(getlelbyhewanresult.get(position).getId());
        holder.lelsapiharga.setText(getlelbyhewanresult.get(position).getHarga());
        holder.lelsapicomment.setText(getlelbyhewanresult.get(position).getComment());

        holder.btndetlelsapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hewanleldet = new Intent(sapicontext, DetailLelangActivity.class);
                hewanleldet.putExtra("lelid",getlelbyhewanresult.get(position).getId());
                sapicontext.startActivity(hewanleldet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getlelbyhewanresult.size();
    }


    public class LelSapiViewHolder extends RecyclerView.ViewHolder{
        TextView lelsapiid,lelsapiharga,lelsapicomment;
        ImageView btndetlelsapi;

        public LelSapiViewHolder(@NonNull View itemView) {
            super(itemView);
            lelsapiid = itemView.findViewById(R.id.idsapi);
            lelsapiharga = itemView.findViewById(R.id.hargasapi);
            lelsapicomment = itemView.findViewById(R.id.commentsapi);
            btndetlelsapi = itemView.findViewById(R.id.btn_detailsapi);
        }
    }
}
