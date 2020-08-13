package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ListlelasPelelangAdapt;
import com.example.tryretrofitlogin.adapter.ListlelasPesertaAdapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getlelaspeserta.GetlelAsPesertaResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyuser.GetlelbrjalanbyuserResponse;
import com.example.tryretrofitlogin.responses.getpesrtmanagerbyuser.GetpesrtmanagerbyuserResponse;

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
        lelaspesertaFilterkey.setText(userfilter_key);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        aspesertaRV.setLayoutManager(layoutManager);
        aspesertaRV.setHasFixedSize(true);

        Getlelpsrtmanager();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void Getlelpsrtmanager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetpesrtmanagerbyuserResponse> call = service.psrtmanagerbyuser(
                lelaspesertaFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetpesrtmanagerbyuserResponse>() {
            @Override
            public void onResponse(Call<GetpesrtmanagerbyuserResponse> call, Response<GetpesrtmanagerbyuserResponse> response) {
                ListlelasPesertaAdapt lelaspeserta = new ListlelasPesertaAdapt(ListLelangAsPeserta.this,response.body().getSuccess());
                lelaspeserta.notifyDataSetChanged();
                aspesertaRV.setAdapter(lelaspeserta);
            }

            @Override
            public void onFailure(Call<GetpesrtmanagerbyuserResponse> call, Throwable t) {

            }
        });
    }
}