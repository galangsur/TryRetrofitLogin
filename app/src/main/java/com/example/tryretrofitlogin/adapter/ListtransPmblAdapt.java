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
import com.example.tryretrofitlogin.activity.DetailTransaksiProduk;
import com.example.tryretrofitlogin.responses.getprdktransbypmbl.SuccessItem;

import java.util.ArrayList;

public class ListtransPmblAdapt extends RecyclerView.Adapter<ListtransPmblAdapt.TransPmblViewHolder> {
    ArrayList<SuccessItem> prdktransbypmbl;
    Context prdktranspmblContex;

    public ListtransPmblAdapt (Context prdktranspmblContex, ArrayList<SuccessItem> prdktransbypmbl){
        super();
        this.prdktransbypmbl = prdktransbypmbl;
        this.prdktranspmblContex = prdktranspmblContex;
    }


    @NonNull
    @Override
    public TransPmblViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listprdktransitem_layout,parent,false);
        return new TransPmblViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransPmblViewHolder holder, final int position) {
        holder.pmbl_txtlistprdkid.setText(prdktransbypmbl.get(position).getId());
        holder.pmbl_txtlistprdkstathslid.setText(prdktransbypmbl.get(position).getStatushasilId());
        holder.pmbl_txtlistprdkhsl.setText(prdktransbypmbl.get(position).getStatushasil());

        holder.pmbl_btndetlistprdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lelpsrttrans = new Intent(prdktranspmblContex, DetailTransaksiProduk.class);
                lelpsrttrans.putExtra("idtransprdk",prdktransbypmbl.get(position).getId());
                prdktranspmblContex.startActivity(lelpsrttrans);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prdktransbypmbl.size();
    }

    public class TransPmblViewHolder extends RecyclerView.ViewHolder {
        TextView pmbl_txtlistprdkid,pmbl_txtlistprdkstathslid,pmbl_txtlistprdkhsl;
        Button pmbl_btndetlistprdk;

        public TransPmblViewHolder(@NonNull View itemView) {
            super(itemView);
            pmbl_txtlistprdkid = itemView.findViewById(R.id.listprdkid);
            pmbl_txtlistprdkstathslid = itemView.findViewById(R.id.listprdkstathasilid);
            pmbl_txtlistprdkhsl = itemView.findViewById(R.id.listprdkstathasil);
            pmbl_btndetlistprdk = itemView.findViewById(R.id.listprdkdetbtn);
        }
    }
}
