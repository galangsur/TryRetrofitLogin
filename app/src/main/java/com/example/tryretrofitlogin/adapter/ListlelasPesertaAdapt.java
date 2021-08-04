package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailLelbrjalan;
import com.example.tryretrofitlogin.responses.getleldiikutipeserta.SuccessItem;

import java.util.ArrayList;

public class ListlelasPesertaAdapt extends RecyclerView.Adapter<ListlelasPesertaAdapt.LelAsPesertaViewHolder> {
    ArrayList<SuccessItem> getpsrtmanager;
    Context psrtmanagerContext;

    public ListlelasPesertaAdapt(Context psrtmanagerContext, ArrayList<SuccessItem> getpsrtmanager ){
        super();
        this.psrtmanagerContext = psrtmanagerContext;
        this.getpsrtmanager = getpsrtmanager;
    }

    @NonNull
    @Override
    public LelAsPesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listlelaspesertaitem_layout,parent,false);
        return new LelAsPesertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelAsPesertaViewHolder holder, final int position) {
        holder.psrtmanager_id.setText(getpsrtmanager.get(position).getId());
        holder.peserta_id.setText(getpsrtmanager.get(position).getPesertaId());
        holder.lelbrjalan_id.setText(getpsrtmanager.get(position).getLelbrjalanId());

        holder.btndettolelbrjalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aspeserta = new Intent(psrtmanagerContext, DetailLelbrjalan.class);
                aspeserta.putExtra("idlelbrjalan",getpsrtmanager.get(position).getLelbrjalanId());
                psrtmanagerContext.startActivity(aspeserta);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getpsrtmanager.size();
    }

    public class LelAsPesertaViewHolder extends RecyclerView.ViewHolder {
        TextView psrtmanager_id, lelbrjalan_id, peserta_id;
        Button btndettolelbrjalan;

        public LelAsPesertaViewHolder(@NonNull View itemView) {
            super(itemView);
            psrtmanager_id = itemView.findViewById(R.id.idpsrtmanager);
            lelbrjalan_id = itemView.findViewById(R.id.id_lelbrjalan);
            peserta_id = itemView.findViewById(R.id.id_peserta);
            btndettolelbrjalan = itemView.findViewById(R.id.btn_todetlelbrjalan);

        }
    }
}
