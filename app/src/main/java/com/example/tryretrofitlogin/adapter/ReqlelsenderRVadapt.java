package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailReqlelActivity;
import com.example.tryretrofitlogin.responses.getreqlelbysender.SuccessItem;

import java.util.ArrayList;

public class ReqlelsenderRVadapt extends RecyclerView.Adapter<ReqlelsenderRVadapt.SenderViewHolder> {
    ArrayList<SuccessItem> senderresult;
    Context sendercontext;

    public ReqlelsenderRVadapt(Context sendercontext, ArrayList<SuccessItem> senderresult){
        super();
        this.sendercontext = sendercontext;
        this.senderresult = senderresult;
    }

    @NonNull
    @Override
    public SenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reqlelitem_layout,parent,false);
        return new SenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SenderViewHolder holder, final int position) {
        holder.lelid.setText(senderresult.get(position).getLelangId());
        holder.pelelangid.setText(senderresult.get(position).getUserId());
        holder.pengirimid.setText(senderresult.get(position).getPengirimId());

        holder.btnreqlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "clickon: " + senderresult.get(position).getId());
                Intent reqleldet = new Intent(sendercontext, DetailReqlelActivity.class);
                reqleldet.putExtra("reqlelid",senderresult.get(position).getId());
                sendercontext.startActivity(reqleldet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return senderresult.size();
    }


    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView lelid,pelelangid,pengirimid;
        Button btnreqlel;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            lelid = itemView.findViewById(R.id.reqlel_lelid);
            pelelangid = itemView.findViewById(R.id.reqlel_pelelangid);
            pengirimid = itemView.findViewById(R.id.reqlel_pengirimid);
            btnreqlel = itemView.findViewById(R.id.btndetreqlel);
        }
    }
}
