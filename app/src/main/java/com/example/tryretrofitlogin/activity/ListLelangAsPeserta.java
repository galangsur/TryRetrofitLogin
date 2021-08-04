package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ListlelasPesertaAdapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getleldiikutipeserta.GetleldiikutipesertaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangAsPeserta extends AppCompatActivity {
    public RecyclerView aspesertaRV;
    private TextView lelaspesertaFilterkey;
    private String userfilter_key;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelang_as_peserta);

        aspesertaRV = (RecyclerView)findViewById(R.id.lelaspesertaRV);
        lelaspesertaFilterkey = (TextView)findViewById(R.id.lelaspeserta_filterkey);
        btnBack = (ImageView)findViewById(R.id.btnback_lelaspeserta);

        userfilter_key = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        Toast.makeText(this, userfilter_key, Toast.LENGTH_SHORT).show();
        lelaspesertaFilterkey.setText(userfilter_key);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        aspesertaRV.setLayoutManager(layoutManager);
        aspesertaRV.setHasFixedSize(true);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getlelpsrtmanager();
            }
        });
    }

    public void Getlelpsrtmanager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetleldiikutipesertaResponse> call = service.psrtmanagerbyuser(
                userfilter_key);

        call.enqueue(new Callback<GetleldiikutipesertaResponse>() {
            @Override
            public void onResponse(Call<GetleldiikutipesertaResponse> call, Response<GetleldiikutipesertaResponse> response) {
                if (response.isSuccessful()){
                    ListlelasPesertaAdapt lelaspeserta = new ListlelasPesertaAdapt(ListLelangAsPeserta.this,response.body().getSuccess());
                    lelaspeserta.notifyDataSetChanged();
                    aspesertaRV.setAdapter(lelaspeserta);
                }else {
                    Toast.makeText(ListLelangAsPeserta.this, ";"+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetleldiikutipesertaResponse> call, Throwable t) {

            }
        });
    }
}