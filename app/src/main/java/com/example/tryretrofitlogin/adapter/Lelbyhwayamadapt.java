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
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.SuccessItem;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Lelbyhwayamadapt extends RecyclerView.Adapter<Lelbyhwayamadapt.LelAyamViewHolder> {
    ArrayList<SuccessItem> getayamresult;
    Context ayamcontext;
    String ImageURL = APIUrl.IMAGE_URL;
    private int hargaayam;

    public Lelbyhwayamadapt(Context ayamcontext, ArrayList<SuccessItem> getayamresult){
        super();
        this.ayamcontext = ayamcontext;
        this.getayamresult = getayamresult;
    }

    @NonNull
    @Override
    public LelAyamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lelbyhewanayam_layout,parent,false);
        return new LelAyamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelAyamViewHolder holder, final int position) {
        holder.lelayamid.setText(getayamresult.get(position).getId());

        hargaayam = getayamresult.get(position).getHarga();
        //ubahformat Rp.
        Locale localID = new Locale("in","ID");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
        holder.lelayamharga.setText(formatRupiah.format((double)hargaayam));

        holder.lelayamcomment.setText(getayamresult.get(position).getComment());
        holder.btndetlelayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ayamleldet = new Intent(ayamcontext, DetailLelangActivity.class);
                ayamleldet.putExtra("lelid",getayamresult.get(position).getId());
                ayamcontext.startActivity(ayamleldet);
            }
        });

        holder.urlphotoayam.setText(getayamresult.get(position).getImgLelang());
        Picasso.with(ayamcontext).
                load(ImageURL+getayamresult.get(position).getImgLelang()).
                fit().
                placeholder(R.drawable.leliconstengah).
                centerInside().
                into(holder.fotohewan);
    }

    @Override
    public int getItemCount() {
        return getayamresult.size();
    }

    public class LelAyamViewHolder extends RecyclerView.ViewHolder {
        TextView lelayamid, lelayamharga, lelayamcomment,urlphotoayam;
        ImageView btndetlelayam, fotohewan;

        public LelAyamViewHolder(@NonNull View itemView) {
            super(itemView);
            lelayamid = itemView.findViewById(R.id.idayam);
            lelayamharga = itemView.findViewById(R.id.hargaayam);
            lelayamcomment = itemView.findViewById(R.id.commentayam);
            btndetlelayam = itemView.findViewById(R.id.btn_detailayam);
            fotohewan = itemView.findViewById(R.id.img_fotohewanayam);
            urlphotoayam = itemView.findViewById(R.id.txt_urlayam);
        }
    }
}
