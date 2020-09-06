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
import com.example.tryretrofitlogin.activity.DetailTransaksiLelang;
import com.example.tryretrofitlogin.responses.getleltransbypllg.SuccessItem;

import java.util.ArrayList;

public class ListleltransPllgAdapt extends RecyclerView.Adapter<ListleltransPllgAdapt.LeltransPllgViewHolder> {
    ArrayList<SuccessItem> leltransbypllg;
    Context leltranspllgContex;

    public ListleltransPllgAdapt (Context leltranspllgContex, ArrayList<SuccessItem> leltransbypllg){
        super();
        this.leltransbypllg = leltransbypllg;
        this.leltranspllgContex = leltranspllgContex;
    }

    @NonNull
    @Override
    public LeltransPllgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listleltranspllgitem_layout,parent,false);
        return new LeltransPllgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeltransPllgViewHolder holder, final int position) {
        holder.txtlelpllgid.setText(leltransbypllg.get(position).getId());
        holder.txtlelpllgstathslid.setText(leltransbypllg.get(position).getStatushasilId());
        holder.txtlelpllghsl.setText(leltransbypllg.get(position).getStatushasil());

        holder.btndetlelpllg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lelpsrttrans = new Intent(leltranspllgContex, DetailTransaksiLelang.class);
                lelpsrttrans.putExtra("idtranslel",leltransbypllg.get(position).getId());
                leltranspllgContex.startActivity(lelpsrttrans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return leltransbypllg.size();
    }

    public class LeltransPllgViewHolder extends RecyclerView.ViewHolder {
        TextView txtlelpllgid,txtlelpllgstathslid,txtlelpllghsl;
        Button btndetlelpllg;

        public LeltransPllgViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlelpllgid = itemView.findViewById(R.id.lelpllgid);
            txtlelpllgstathslid = itemView.findViewById(R.id.lelpllgstathasilid);
            txtlelpllghsl = itemView.findViewById(R.id.lelpllgstathasil);
            btndetlelpllg = itemView.findViewById(R.id.lelpllgdetbtn);
        }
    }
}
