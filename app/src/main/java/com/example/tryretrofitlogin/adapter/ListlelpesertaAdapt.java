package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan.SuccessItem;

import java.util.ArrayList;

public class ListlelpesertaAdapt extends RecyclerView.Adapter<ListlelpesertaAdapt.ListlelpesertaViewHolder> {
    ArrayList<SuccessItem> getlelpesertabyidlelberjalan;
    Context listpesertacontex;

    public ListlelpesertaAdapt(Context listpesertacontex, ArrayList<SuccessItem> getlelpesertabyidlelberjalan){
        super();
        this.listpesertacontex = listpesertacontex;
        this.getlelpesertabyidlelberjalan = getlelpesertabyidlelberjalan;
    }

    @NonNull
    @Override
    public ListlelpesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listlelpeserta_layout,parent,false);
        return new ListlelpesertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListlelpesertaViewHolder holder, final int position) {
        holder.txtIdpeserta.setText(getlelpesertabyidlelberjalan.get(position).getPesertaId());
        holder.txtListpesertanama.setText(getlelpesertabyidlelberjalan.get(position).getPesertaNama());

        holder.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(listpesertacontex, "idpeserta" + getlelpesertabyidlelberjalan.get(position).getPesertaId(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return getlelpesertabyidlelberjalan.size();
    }


    public class ListlelpesertaViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdpeserta, txtListpesertanama;
        Button btnProfile;

        public ListlelpesertaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdpeserta = itemView.findViewById(R.id.txt_idpesertalistpeserta);
            txtListpesertanama = itemView.findViewById(R.id.txt_listpesertanama);
            btnProfile = itemView.findViewById(R.id.listpeserta_btnprofile);
        }
    }
}
