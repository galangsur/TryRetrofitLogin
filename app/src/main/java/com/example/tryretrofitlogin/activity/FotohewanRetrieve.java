package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.HorizImageRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getimgbyparent.GetimgbyparentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotohewanRetrieve extends AppCompatActivity {

    private RecyclerView imgRVhewan;
    private String imghewtoken;
    private TextView txt_imghewtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotohewan_retrieve);

        imgRVhewan = (RecyclerView) findViewById(R.id.RV_hewanfoto);
        txt_imghewtoken = (TextView) findViewById(R.id.tmpkeyimghewan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,false);
        imgRVhewan.setLayoutManager(layoutManager);
        imgRVhewan.setHasFixedSize(true);

        Intent fotohewan = getIntent();
        imghewtoken = fotohewan.getStringExtra("imgfotohw");
        txt_imghewtoken.setText(imghewtoken);
//        Toast.makeText(this, "" + txt_imghewtoken.getText().toString().trim(), Toast.LENGTH_SHORT).show();

        getAllImage();
    }

    public void getAllImage(){
        String key = txt_imghewtoken.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetimgbyparentResponse> call = service.getimgbyparent(
                key
        );

        call.enqueue(new Callback<GetimgbyparentResponse>() {
            @Override
            public void onResponse(Call<GetimgbyparentResponse> call, Response<GetimgbyparentResponse> response) {
                if (response.isSuccessful()){
                    HorizImageRVadapt horizImageRVadapt =  new HorizImageRVadapt(FotohewanRetrieve.this,response.body()
                            .getSuccess());
                    horizImageRVadapt.notifyDataSetChanged();
                    imgRVhewan.setAdapter(horizImageRVadapt);
                }
            }

            @Override
            public void onFailure(Call<GetimgbyparentResponse> call, Throwable t) {
                Toast.makeText(FotohewanRetrieve.this, "gkkeget", Toast.LENGTH_SHORT).show();
            }
        });
    }
}