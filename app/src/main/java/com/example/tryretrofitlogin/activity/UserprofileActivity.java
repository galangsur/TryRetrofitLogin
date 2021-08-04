package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Ratingnreviewadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getalldatauserbyid.GetalldatauserbyidResponse;
import com.example.tryretrofitlogin.responses.getratingreviewbytoken.Getreviewratingbytoken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserprofileActivity extends AppCompatActivity {
    private TextView txtuserprof_namauser,txtuserporf_emailuser, txtuserprof_tlpuser;
    private TextView tmpuserid_userporf, tmpratingtoken_userprof;
    private RecyclerView ratingnreviewRV;
    private String userprof_iduser,userporf_ratingtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        txtuserprof_namauser = (TextView) findViewById(R.id.txtprofile_namauser);
        txtuserporf_emailuser = (TextView) findViewById(R.id.txtprofile_email);
        txtuserprof_tlpuser = (TextView) findViewById(R.id.txtprofile_notlp);
        tmpuserid_userporf = (TextView) findViewById(R.id.tmpuserprof_iduser);
        tmpratingtoken_userprof = (TextView) findViewById(R.id.tmpuserprof_ratingtoken);
        ratingnreviewRV = (RecyclerView) findViewById(R.id.userprofilereviewRV);

        Intent lelintent = getIntent();
        userprof_iduser = lelintent.getStringExtra("useridfrom_dettrans");
        tmpuserid_userporf.setText(userprof_iduser);

        RecyclerView.LayoutManager userproflayoutmanager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        ratingnreviewRV.setLayoutManager(userproflayoutmanager);
        ratingnreviewRV.setHasFixedSize(true);

        getuserbyid();
        getreviewnrating();
    }


    private void getreviewnrating(){
        String rnrtoken = tmpratingtoken_userprof.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Getreviewratingbytoken> call = service.getreviewratingbytoken(
                rnrtoken);

        call.enqueue(new Callback<Getreviewratingbytoken>() {
            @Override
            public void onResponse(Call<Getreviewratingbytoken> call, Response<Getreviewratingbytoken> response) {
                Ratingnreviewadapt ratingnreviewadapt = new Ratingnreviewadapt(UserprofileActivity.this,response.body().getSuccess());
                ratingnreviewadapt.notifyDataSetChanged();
                ratingnreviewRV.setAdapter(ratingnreviewadapt);
            }

            @Override
            public void onFailure(Call<Getreviewratingbytoken> call, Throwable t) {

            }
        });
    }

    private void getuserbyid(){
        String iduser = tmpuserid_userporf.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetalldatauserbyidResponse> call = service.getalldatauserbyid(
                iduser);

        call.enqueue(new Callback<GetalldatauserbyidResponse>() {
            @Override
            public void onResponse(Call<GetalldatauserbyidResponse> call, Response<GetalldatauserbyidResponse> response) {
                tmpratingtoken_userprof.setText(response.body().getSuccess().getRatingnreview_token());
            }

            @Override
            public void onFailure(Call<GetalldatauserbyidResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }
}