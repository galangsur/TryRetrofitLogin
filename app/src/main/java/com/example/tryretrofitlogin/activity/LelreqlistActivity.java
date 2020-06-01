package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ReqlelRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getreqlel.GetReqlelResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.GetreqlelbyuserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LelreqlistActivity extends AppCompatActivity {

    public RecyclerView reqlelrview;
    private TextView reqlelFilter;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelreqlist);

        reqlelrview = (RecyclerView) findViewById(R.id.reqlelRV);
        reqlelFilter = (TextView) findViewById(R.id.reqlel_filterkey);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        reqlelFilter.setText(userid);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        reqlelrview.setLayoutManager(layoutManager);
        reqlelrview.setHasFixedSize(true);
        getDataReqlel();
    }

    public void getDataReqlel(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetreqlelbyuserResponse> call = service.getReqlelbyuser(
                reqlelFilter.getText().toString().trim());

        call.enqueue(new Callback<GetreqlelbyuserResponse>() {
            @Override
            public void onResponse(Call<GetreqlelbyuserResponse> call, Response<GetreqlelbyuserResponse> response) {
                ReqlelRVadapt reqlelRVadapt = new ReqlelRVadapt(LelreqlistActivity.this,response.body().getSuccess());
                reqlelRVadapt.notifyDataSetChanged();
                reqlelrview.setAdapter(reqlelRVadapt);
            }

            @Override
            public void onFailure(Call<GetreqlelbyuserResponse> call, Throwable t) {

            }
        });
    }
}
