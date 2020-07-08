package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Lelbyhwayamadapt;
import com.example.tryretrofitlogin.adapter.Lelbyhwsapiadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangayamActivity extends AppCompatActivity {
    private RecyclerView lelayamrview;
    private TextView lelayamFilterkey;
    private String hewanid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelang_ayam);

        lelayamrview = (RecyclerView) findViewById(R.id.lelangayamRV);
        lelayamFilterkey = (TextView)findViewById(R.id.lelayam_filterkey);

        Intent intenthewan = getIntent();
        hewanid = intenthewan.getStringExtra("ayamid");
        lelayamFilterkey.setText(hewanid);
        Toast.makeText(this, hewanid, Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        lelayamrview.setLayoutManager(layoutManager);
        lelayamrview.setHasFixedSize(true);

        GetlelangAyam();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void GetlelangAyam(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyhewanResponse> call = service.getLelbyhewan(
                lelayamFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetlelangbyhewanResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyhewanResponse> call, Response<GetlelangbyhewanResponse> response) {
                Lelbyhwayamadapt reqbyhwayam = new Lelbyhwayamadapt(ListLelangayamActivity.this,response.body().getSuccess());
                reqbyhwayam.notifyDataSetChanged();
                lelayamrview.setAdapter(reqbyhwayam);
            }

            @Override
            public void onFailure(Call<GetlelangbyhewanResponse> call, Throwable t) {

            }
        });
    }
}