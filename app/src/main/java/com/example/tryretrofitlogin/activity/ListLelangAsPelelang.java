package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Lelbyhwsapiadapt;
import com.example.tryretrofitlogin.adapter.ListlelasPelelangAdapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyuser.GetlelbrjalanbyuserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangAsPelelang extends AppCompatActivity {
    public RecyclerView aspelelangRV;
    private TextView lelaspelelangFilterkey;
    private String userfilter_key;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelang_as_pelelang);

        aspelelangRV = (RecyclerView) findViewById(R.id.lelaspelelangRV);
        lelaspelelangFilterkey = (TextView)findViewById(R.id.lelaspelelang_filterkey);
        btnBack = (ImageView)findViewById(R.id.btnback_lelaspelelang);

        userfilter_key = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        lelaspelelangFilterkey.setText(userfilter_key);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        aspelelangRV.setLayoutManager(layoutManager);
        aspelelangRV.setHasFixedSize(true);

        Getlelangbrjalan();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void Getlelangbrjalan(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelbrjalanbyuserResponse> call = service.getlelbrjlanbyuser(
                lelaspelelangFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetlelbrjalanbyuserResponse>() {
            @Override
            public void onResponse(Call<GetlelbrjalanbyuserResponse> call, Response<GetlelbrjalanbyuserResponse> response) {
                ListlelasPelelangAdapt lelaspelelang = new ListlelasPelelangAdapt(ListLelangAsPelelang.this,response.body().getSuccess());
                lelaspelelang.notifyDataSetChanged();
                aspelelangRV.setAdapter(lelaspelelang);
            }

            @Override
            public void onFailure(Call<GetlelbrjalanbyuserResponse> call, Throwable t) {

            }
        });
    }
}