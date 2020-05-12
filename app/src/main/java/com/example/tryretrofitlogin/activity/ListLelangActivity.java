package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.RVadapter;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getlelang.GetlelangResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangActivity extends AppCompatActivity {

    public RecyclerView rview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelang);

        rview = (RecyclerView) findViewById(R.id.lelangRV);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        rview.setLayoutManager(layoutManager);
        rview.setHasFixedSize(true);
        getDataLelang();
    }

    public void getDataLelang() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangResponse> call = service.getlelang();

        call.enqueue(new Callback<GetlelangResponse>() {
            @Override
            public void onResponse(Call<GetlelangResponse> call, Response<GetlelangResponse> response) {
                RVadapter vadapter = new RVadapter(ListLelangActivity.this,response.body().getSuccess());
                vadapter.notifyDataSetChanged();
                rview.setAdapter(vadapter);
            }

            @Override
            public void onFailure(Call<GetlelangResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
