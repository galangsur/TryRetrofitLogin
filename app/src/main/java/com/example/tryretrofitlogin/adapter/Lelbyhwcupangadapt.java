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
import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.SuccessItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Lelbyhwcupangadapt extends RecyclerView.Adapter<Lelbyhwcupangadapt.LelCupangViewHolder> {
    ArrayList<SuccessItem> getcupangresult;
    Context cupangcontext;
    private int hargacupang;

    public Lelbyhwcupangadapt(Context cupangcontext, ArrayList<SuccessItem> getcupangresult){
        super();
        this.cupangcontext = cupangcontext;
        this.getcupangresult = getcupangresult;
    }

    @NonNull
    @Override
    public LelCupangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lelbyhewancupang_layout,parent,false);
        return new LelCupangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelCupangViewHolder holder, final int position) {
        holder.lelcupangid.setText(getcupangresult.get(position).getId());

        hargacupang = getcupangresult.get(position).getHarga();

        //ubahformat Rp.
        Locale localID = new Locale("in","ID");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
        holder.lelcupangharga.setText(formatRupiah.format((double)hargacupang));

        holder.lelcupangcomment.setText(getcupangresult.get(position).getComment());
        holder.btndetlelcupang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hewanleldet = new Intent(cupangcontext, DetailLelangActivity.class);
                hewanleldet.putExtra("lelid",getcupangresult.get(position).getId());
                cupangcontext.startActivity(hewanleldet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getcupangresult.size();
    }


    public class LelCupangViewHolder extends RecyclerView.ViewHolder{
        TextView lelcupangid,lelcupangharga,lelcupangcomment;
        ImageView btndetlelcupang;

        public LelCupangViewHolder(@NonNull View itemView) {
            super(itemView);
            lelcupangid = itemView.findViewById(R.id.idcupang);
            lelcupangharga = itemView.findViewById(R.id.hargacupang);
            lelcupangcomment = itemView.findViewById(R.id.commentcupang);
            btndetlelcupang = itemView.findViewById(R.id.btn_detailcupang);
        }
    }
}
