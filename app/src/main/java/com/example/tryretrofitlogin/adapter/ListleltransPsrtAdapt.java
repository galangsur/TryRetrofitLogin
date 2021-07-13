package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailTransaksiLelang;
import com.example.tryretrofitlogin.responses.getleltransbypsrt.SuccessItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListleltransPsrtAdapt extends RecyclerView.Adapter<ListleltransPsrtAdapt.LeltransPsrtViewHolder> {
    ArrayList<SuccessItem> leltransbypsrt;
    Context leltranspsrtContex;
    private int hargaakhirpsrt;

    public ListleltransPsrtAdapt (Context leltranspsrtContex, ArrayList<SuccessItem> leltransbypsrt){
        super();
        this.leltranspsrtContex = leltranspsrtContex;
        this.leltransbypsrt = leltransbypsrt;
    }

    @NonNull
    @Override
    public LeltransPsrtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listleltranspsrtitem_layout,parent,false);
        return new  LeltransPsrtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeltransPsrtViewHolder holder, final int position) {
        holder.txtlelpsrtid.setText(leltransbypsrt.get(position).getId());
        holder.txtlelpsrtstathslid.setText(leltransbypsrt.get(position).getStatushasilId());
        holder.txtlelpsrthsl.setText(leltransbypsrt.get(position).getStatushasil());

        hargaakhirpsrt = leltransbypsrt.get(position).getNilaiAkhir();
        //ubahformat Rp.
        Locale localID = new Locale("in","ID");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
        holder.txtlelpsrthslharga.setText(formatRupiah.format((double)hargaakhirpsrt));

        holder.btndetlelpsrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lelpsrttrans = new Intent(leltranspsrtContex, DetailTransaksiLelang.class);
                lelpsrttrans.putExtra("idtranslel",leltransbypsrt.get(position).getId());
                leltranspsrtContex.startActivity(lelpsrttrans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return leltransbypsrt.size();
    }

    public class LeltransPsrtViewHolder extends RecyclerView.ViewHolder {
        TextView txtlelpsrtid,txtlelpsrtstathslid,txtlelpsrthsl,txtlelpsrthslharga;
        Button btndetlelpsrt;

        public LeltransPsrtViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlelpsrtid = itemView.findViewById(R.id.lelpsrtid);
            txtlelpsrtstathslid = itemView.findViewById(R.id.lelpsrtstathasilid);
            txtlelpsrthsl = itemView.findViewById(R.id.lelpsrtstathasil);
            txtlelpsrthslharga = itemView.findViewById(R.id.hargahewanpsrt);
            btndetlelpsrt = itemView.findViewById(R.id.lelpsrtdetbtn);
        }
    }
}
