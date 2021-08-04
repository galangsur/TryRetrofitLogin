package com.example.tryretrofitlogin.adapter;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
;
import com.example.tryretrofitlogin.models.PesertaOnlineStatus;
import com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan.SuccessItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pesertaonlineadapt extends RecyclerView.Adapter<Pesertaonlineadapt.PesertaonlineViewHolder> {
    ArrayList<SuccessItem> getpsrtonlinemanager;
    Context psrtonlinemanagerContext;

    public Pesertaonlineadapt(Context psrtonlinemanagerContext, ArrayList<SuccessItem> getpsrtonlinemanager ){
        super();
        this.psrtonlinemanagerContext = psrtonlinemanagerContext;
        this.getpsrtonlinemanager = getpsrtonlinemanager;
    }

    @NonNull
    @Override
    public PesertaonlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listpesertaonline_layout,parent,false);
        return new PesertaonlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesertaonlineViewHolder holder, int position) {
        holder.txtIdpesertaonline.setText(getpsrtonlinemanager.get(position).getPesertaId());
        holder.txtListpesertanamaonline.setText(getpsrtonlinemanager.get(position).getPesertaNama());

        String childpesertastatus = "peserta"+getpsrtonlinemanager.get(position).getPesertaId();
        DatabaseReference tmppeserta = FirebaseDatabase.getInstance().getReference().child("PesertaOnlineManager").child("qwerty")
                .child(childpesertastatus);
        tmppeserta.child("status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("online")){
                    holder.txtInroomstatus.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                } else if (snapshot.getValue().toString().equals("offline")){
                    holder.txtInroomstatus.setVisibility(View.INVISIBLE);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return getpsrtonlinemanager.size();
    }

    public class PesertaonlineViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdpesertaonline, txtListpesertanamaonline,txtInroomstatus;

        public PesertaonlineViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdpesertaonline = itemView.findViewById(R.id.txt_idpesertalistpesertaonline);
            txtListpesertanamaonline = itemView.findViewById(R.id.txt_listpesertanamaonline);
            txtInroomstatus = itemView.findViewById(R.id.pesertaonline_status);
        }
    }
}
