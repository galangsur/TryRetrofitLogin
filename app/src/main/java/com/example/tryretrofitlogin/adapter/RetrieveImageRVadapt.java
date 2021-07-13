package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getimgbyparent.SuccessItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RetrieveImageRVadapt extends RecyclerView.Adapter<RetrieveImageRVadapt.HewViewholder> {
    ArrayList<SuccessItem> imagehewresult;
    Context imghewCtx;
    String ImageHwURL = APIUrl.IMAGE_URL;

    public RetrieveImageRVadapt(Context imghewCtx, ArrayList imagehewresult){
        super();
        this.imghewCtx = imghewCtx;
        this.imagehewresult = imagehewresult;
    }

    @NonNull
    @Override
    public HewViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imageitem_layout,parent,false);
        return new HewViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HewViewholder holder, int position) {
        holder.urlhw.setText(imagehewresult.get(position).getPhoto());
        Picasso.with(imghewCtx)
                .load(ImageHwURL+imagehewresult.get(position).getPhoto())
                .fit()
                .centerInside()
                .into(holder.imagehw);
    }

    @Override
    public int getItemCount() {
        return imagehewresult.size();
    }

    public class HewViewholder extends RecyclerView.ViewHolder {

        ImageView imagehw;
        TextView urlhw;

        public HewViewholder(@NonNull View itemView) {
            super(itemView);
            imagehw = itemView.findViewById(R.id.img_ImageView);
            urlhw = itemView.findViewById(R.id.txt_url);
        }
    }
}
