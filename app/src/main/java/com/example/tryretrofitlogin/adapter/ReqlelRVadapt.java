package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.DetailLelangActivity;
import com.example.tryretrofitlogin.activity.DetailReqlelActivity;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.SuccessItem;

import java.util.ArrayList;

public class ReqlelRVadapt extends RecyclerView.Adapter<ReqlelRVadapt.ReqlelViewHolder> {

    ArrayList<SuccessItem> reqlelresult;
    Context reqlelcontext;

    public ReqlelRVadapt(Context reqlelcontext, ArrayList<SuccessItem> reqlelresult){
        super();
        this.reqlelresult = reqlelresult;
        this.reqlelcontext = reqlelcontext;
    }

    @Override
    public ReqlelRVadapt.ReqlelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reqlelitem_layout,parent,false);
        return new ReqlelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReqlelViewHolder holder, final int position) {
        holder.lelid.setText(reqlelresult.get(position).getLelangId());
        holder.pelelangid.setText(reqlelresult.get(position).getUserId());
        holder.pengirimid.setText(reqlelresult.get(position).getPengirimId());

        holder.btnreqlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "clickon: " + reqlelresult.get(position).getId());
                Intent reqleldet = new Intent(reqlelcontext, DetailReqlelActivity.class);
                reqleldet.putExtra("reqlelid",reqlelresult.get(position).getId());
                reqlelcontext.startActivity(reqleldet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reqlelresult.size();
    }

    public class ReqlelViewHolder extends RecyclerView.ViewHolder {
        TextView lelid,pelelangid,pengirimid;
        Button btnreqlel;

        public ReqlelViewHolder(View itemView) {
            super(itemView);
            lelid = itemView.findViewById(R.id.reqlel_lelid);
            pelelangid = itemView.findViewById(R.id.reqlel_pelelangid);
            pengirimid = itemView.findViewById(R.id.reqlel_pengirimid);
            btnreqlel = itemView.findViewById(R.id.btndetreqlel);
        }
    }
}
