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
import com.example.tryretrofitlogin.activity.DetailTransaksiProduk;
import com.example.tryretrofitlogin.responses.getprdktransbypnjl.SuccessItem;

import java.util.ArrayList;

public class ListtransPnjlAdapt extends RecyclerView.Adapter<ListtransPnjlAdapt.TransPnjlViewHolder> {
    ArrayList<SuccessItem> prdktransbypnjl;
    Context prdktranspnjlContex;

    public ListtransPnjlAdapt (Context prdktranspnjlContex, ArrayList<SuccessItem> prdktransbypnjl){
        super();
        this.prdktransbypnjl = prdktransbypnjl;
        this.prdktranspnjlContex = prdktranspnjlContex;
    }

    @NonNull
    @Override
    public TransPnjlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listprdktransitem_layout,parent,false);
        return new TransPnjlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransPnjlViewHolder holder, final int position) {
        holder.pnjl_txtlistprdkid.setText(prdktransbypnjl.get(position).getId());
        holder.pnjl_txtlistprdkstathslid.setText(prdktransbypnjl.get(position).getStatushasilId());
        holder.pnjl_txtlistprdkhsl.setText(prdktransbypnjl.get(position).getStatushasil());

        holder.pnjl_btndetlistprdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lelpsrttrans = new Intent(prdktranspnjlContex, DetailTransaksiProduk.class);
                lelpsrttrans.putExtra("idtransprdk",prdktransbypnjl.get(position).getId());
                prdktranspnjlContex.startActivity(lelpsrttrans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prdktransbypnjl.size();
    }

    public class TransPnjlViewHolder extends RecyclerView.ViewHolder {
        TextView pnjl_txtlistprdkid,pnjl_txtlistprdkstathslid,pnjl_txtlistprdkhsl;
        Button pnjl_btndetlistprdk;

        public TransPnjlViewHolder(@NonNull View itemView) {
            super(itemView);
            pnjl_txtlistprdkid = itemView.findViewById(R.id.listprdkid);
            pnjl_txtlistprdkstathslid = itemView.findViewById(R.id.listprdkstathasilid);
            pnjl_txtlistprdkhsl = itemView.findViewById(R.id.listprdkstathasil);
            pnjl_btndetlistprdk = itemView.findViewById(R.id.listprdkdetbtn);
        }
    }
}
