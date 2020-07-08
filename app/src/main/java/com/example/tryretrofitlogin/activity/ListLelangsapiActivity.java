package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Lelbyhwsapiadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangsapiActivity extends AppCompatActivity {
    private RecyclerView lelsapirview;
    private TextView lelsapiFilterkey;
    private String hewanid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelangsapi);

        lelsapirview = (RecyclerView) findViewById(R.id.lelangsapiRV);
        lelsapiFilterkey = (TextView)findViewById(R.id.lelsapi_filterkey);

        Intent intenthewan = getIntent();
        hewanid = intenthewan.getStringExtra("sapiid");
        lelsapiFilterkey.setText(hewanid);
        Toast.makeText(this, lelsapiFilterkey.getText().toString(), Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        lelsapirview.setLayoutManager(layoutManager);
        lelsapirview.setHasFixedSize(true);

        GetlelangSapi();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void GetlelangSapi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyhewanResponse> call = service.getLelbyhewan(
                lelsapiFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetlelangbyhewanResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyhewanResponse> call, Response<GetlelangbyhewanResponse> response) {
                Lelbyhwsapiadapt reqbyhwsapi = new Lelbyhwsapiadapt(ListLelangsapiActivity.this,response.body().getSuccess());
                reqbyhwsapi.notifyDataSetChanged();
                lelsapirview.setAdapter(reqbyhwsapi);
            }

            @Override
            public void onFailure(Call<GetlelangbyhewanResponse> call, Throwable t) {

            }
        });
    }
}