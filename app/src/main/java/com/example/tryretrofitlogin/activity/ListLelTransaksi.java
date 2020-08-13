package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ReqlelRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getlistleltransbypeserta.GetListLelbypesertaResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.GetreqlelbyuserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelTransaksi extends AppCompatActivity {

    public RecyclerView listleltransrview;
    private TextView listleltransFilter;
    private String userid;
    private ImageView backlistleltrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_leltransaksi);

        listleltransrview = (RecyclerView) findViewById(R.id.leltransaksiRV);
        listleltransFilter = (TextView) findViewById(R.id.leltrans_filterkey);
        backlistleltrans = (ImageView) findViewById(R.id.btn_backleltranslist);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        listleltransFilter.setText(userid);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        listleltransrview.setLayoutManager(layoutManager);
        listleltransrview.setHasFixedSize(true);
    }

    public void getLeltransbypeserta(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetListLelbypesertaResponse> call = service.getlistleltrans(
                listleltransFilter.getText().toString().trim());

        call.enqueue(new Callback<GetListLelbypesertaResponse>() {
            @Override
            public void onResponse(Call<GetListLelbypesertaResponse> call, Response<GetListLelbypesertaResponse> response) {
            }

            @Override
            public void onFailure(Call<GetListLelbypesertaResponse> call, Throwable t) {

            }
        });
    }
}