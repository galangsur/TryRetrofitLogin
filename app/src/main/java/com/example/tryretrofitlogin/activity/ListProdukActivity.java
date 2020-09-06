package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ProdukRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getallproduk.GetallprodukResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListProdukActivity extends AppCompatActivity {
    private RecyclerView prdkRView;
    private ImageView btnBack, btnAddprdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        prdkRView = (RecyclerView) findViewById(R.id.pasarhewanRV);
        btnBack = (ImageView) findViewById(R.id.btn_backproduk);
        btnAddprdk = (ImageView) findViewById(R.id.btn_pengajuanpasar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        prdkRView.setLayoutManager(layoutManager);
        prdkRView.setHasFixedSize(true);

        Getallproduk();

        btnAddprdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddprdk();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Getallproduk(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetallprodukResponse> call = service.getproduk();

        call.enqueue(new Callback<GetallprodukResponse>() {
            @Override
            public void onResponse(Call<GetallprodukResponse> call, Response<GetallprodukResponse> response) {
                ProdukRVadapt produkRVadapt = new ProdukRVadapt(ListProdukActivity.this,response.body().getSuccess());
                produkRVadapt.notifyDataSetChanged();
                prdkRView.setAdapter(produkRVadapt);
            }

            @Override
            public void onFailure(Call<GetallprodukResponse> call, Throwable t) {

            }
        });
    }

    private void toAddprdk(){
        Intent intent = new Intent(ListProdukActivity.this, AddProdukActivity.class);
        startActivity(intent);
    }
}