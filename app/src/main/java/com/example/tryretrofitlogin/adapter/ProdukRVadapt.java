package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailLelangActivity;
import com.example.tryretrofitlogin.responses.getallproduk.SuccessItem;

import java.util.ArrayList;

public class ProdukRVadapt extends RecyclerView.Adapter<ProdukRVadapt.ProdukViewHolder> {

    ArrayList<SuccessItem> produkresult;
    Context produkcontext;

    public ProdukRVadapt(Context produkcontext, ArrayList<SuccessItem> produkresult){
        super();
        this.produkresult = produkresult;
        this.produkcontext = produkcontext;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listprodukitem_layout,parent,false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, final int position) {
        holder.prdkid.setText(produkresult.get(position).getId());
        holder.prdkharga.setText(produkresult.get(position).getHargaProduk());
        holder.prdktentang.setText(produkresult.get(position).getTentangProduk());

//        holder.btndetprdk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent prdkdet = new Intent(produkcontext, DetailLelangActivity.class);
//                prdkdet.putExtra("prdkid",produkresult.get(position).getId());
//                produkresult.s;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return produkresult.size();
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {

        TextView prdkid, prdkharga, prdktentang;
        ImageView btndetprdk;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            prdkid = itemView.findViewById(R.id.idproduk);
            prdkharga = itemView.findViewById(R.id.hargahewan);
            prdktentang = itemView.findViewById(R.id.tentang_produk);
            btndetprdk = itemView.findViewById(R.id.btn_detailprduk);
        }
    }
}
