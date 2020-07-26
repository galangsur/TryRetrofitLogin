package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getallimage.SuccessItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HorizImageRVadapt extends RecyclerView.Adapter<HorizImageRVadapt.ViewHolder> {
    ArrayList<SuccessItem> imageresult;
    Context imgContext;
    String ImageURL = APIUrl.IMAGE_URL;

    public HorizImageRVadapt(Context imgContext, ArrayList imageresult){
        super();
        this.imgContext = imgContext;
        this.imageresult = imageresult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imageitem_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.url.setText(imageresult.get(position).getPhoto());
        Picasso.with(imgContext)
                .load(ImageURL+imageresult.get(position).getPhoto())
                .resize(300, 130)
                .centerInside()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageresult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_ImageView);
            url = itemView.findViewById(R.id.txt_url);
        }
    }
}
