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

public class Lelbyhwburungadapt extends RecyclerView.Adapter<Lelbyhwburungadapt.LelBurungViewHolder> {
    ArrayList<SuccessItem> getburungresult;
    Context burungcontext;
    private int hargaburung;

    public Lelbyhwburungadapt(Context burungcontext, ArrayList<SuccessItem> getburungresult){
        super();
        this.burungcontext = burungcontext;
        this.getburungresult = getburungresult;
    }

    @NonNull
    @Override
    public LelBurungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lelbyhewanburung_layout,parent,false);
        return new LelBurungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelBurungViewHolder holder, final int position) {
        holder.lelburungid.setText(getburungresult.get(position).getId());

        hargaburung = getburungresult.get(position).getHarga();
        //ubahformat Rp.
        Locale localID = new Locale("in","ID");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
        holder.lelburungharga.setText(formatRupiah.format((double)hargaburung));

        holder.lelburungcomment.setText(getburungresult.get(position).getComment());
        holder.btndetlelburung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burungleldet = new Intent(burungcontext, DetailLelangActivity.class);
                burungleldet.putExtra("lelid",getburungresult.get(position).getId());
                burungcontext.startActivity(burungleldet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getburungresult.size();
    }

    public class LelBurungViewHolder extends RecyclerView.ViewHolder {
        TextView lelburungid, lelburungharga, lelburungcomment;
        ImageView btndetlelburung;

        public LelBurungViewHolder(@NonNull View itemView) {
            super(itemView);
            lelburungid = itemView.findViewById(R.id.idburung);
            lelburungharga = itemView.findViewById(R.id.hargaburung);
            lelburungcomment = itemView.findViewById(R.id.commentburung);
            btndetlelburung = itemView.findViewById(R.id.btn_detailburung);
        }
    }
}
