package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.icu.text.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.responses.getratingreviewbytoken.SuccessItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Ratingnreviewadapt extends RecyclerView.Adapter<Ratingnreviewadapt.RatingnreviewViewHolder> {
    ArrayList<SuccessItem> getratingnreview;
    Context ratingnreviewcontext;

    public Ratingnreviewadapt(Context ratingnreviewcontext, ArrayList<SuccessItem> getratingnreview){
        super();
        this.ratingnreviewcontext = ratingnreviewcontext;
        this.getratingnreview = getratingnreview;
    }

    @NonNull
    @Override
    public RatingnreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listratingnreview_layout,parent,false);
        return new RatingnreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingnreviewViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String createdAt = formatter.format(getratingnreview.get(position).getCreatedAt());


        holder.txtNamareviewer.setText(getratingnreview.get(position).getNama_reviewer());
        holder.ratbarRating.setRating(getratingnreview.get(position).getRating());
        holder.txtReview.setText(getratingnreview.get(position).getReview());
        holder.txtWaktureview.setText(createdAt);
    }

    @Override
    public int getItemCount() {
        return getratingnreview.size();
    }

    public class RatingnreviewViewHolder extends RecyclerView.ViewHolder {
        TextView txtReview,txtWaktureview,txtNamareviewer;
        RatingBar ratbarRating;

        public RatingnreviewViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamareviewer = itemView.findViewById(R.id.txt_namareveiwer);
            txtWaktureview = itemView.findViewById(R.id.txt_datetimereview);
            txtReview = itemView.findViewById(R.id.txt_Rnrreview);
            ratbarRating = itemView.findViewById(R.id.ratbar_Rnrratingbar);
        }
    }
}
