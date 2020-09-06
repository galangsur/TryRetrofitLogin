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
import com.example.tryretrofitlogin.adapter.ListlelasPelelangAdapt;
import com.example.tryretrofitlogin.adapter.ListleltransPllgAdapt;
import com.example.tryretrofitlogin.adapter.ListleltransPsrtAdapt;
import com.example.tryretrofitlogin.adapter.ReqlelRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getleltransbypllg.GetleltransbypllgResponse;
import com.example.tryretrofitlogin.responses.getleltransbypsrt.GetleltransbypsrtResponse;
import com.example.tryretrofitlogin.responses.getlistleltransbypeserta.GetListLelbypesertaResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.GetreqlelbyuserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelTransaksi extends AppCompatActivity {
    //disini ada exeption retrofit
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

    @Override
    protected void onStart() {
        super.onStart();
        getLeltrans();
        backlistleltrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void getLeltrans(){
        final String getpllgkey = listleltransFilter.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final APIService service = retrofit.create(APIService.class);

        Call<GetleltransbypllgResponse> call = service.leltransbypllg(
                getpllgkey
        );

        call.enqueue(new Callback<GetleltransbypllgResponse>() {
            @Override
            public void onResponse(Call<GetleltransbypllgResponse> call, Response<GetleltransbypllgResponse> response) {
                if (response.isSuccessful()){
                    ListleltransPllgAdapt listleltransPllgAdapt = new ListleltransPllgAdapt(ListLelTransaksi.this,response.body().getSuccess());
                    if (listleltransPllgAdapt.getItemCount() !=0){
                        listleltransPllgAdapt.notifyDataSetChanged();
                        listleltransrview.setAdapter(listleltransPllgAdapt);
//                        Toast.makeText(ListLelTransaksi.this, "pllgget", Toast.LENGTH_SHORT).show();
                    } else if (response != null){
                        Call<GetleltransbypsrtResponse> call1 = service.leltransbypsrt(
                          getpllgkey
                        );
                        call1.enqueue(new Callback<GetleltransbypsrtResponse>() {
                            @Override
                            public void onResponse(Call<GetleltransbypsrtResponse> call, Response<GetleltransbypsrtResponse> response) {
                                if (response.isSuccessful()){
                                    ListleltransPsrtAdapt listleltransPsrtAdapt = new ListleltransPsrtAdapt(ListLelTransaksi.this,response.body().getSuccess());
                                    listleltransPsrtAdapt.notifyDataSetChanged();
                                    listleltransrview.setAdapter(listleltransPsrtAdapt);
//                                    Toast.makeText(ListLelTransaksi.this, "psrtget", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<GetleltransbypsrtResponse> call, Throwable t) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<GetleltransbypllgResponse> call, Throwable t) {

            }
        });
    }

    public void getLeltransbypeserta(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetleltransbypsrtResponse> call = service.leltransbypsrt(
                listleltransFilter.getText().toString().trim());

        call.enqueue(new Callback<GetleltransbypsrtResponse>() {
            @Override
            public void onResponse(Call<GetleltransbypsrtResponse> call, Response<GetleltransbypsrtResponse> response) {
                ListleltransPsrtAdapt listleltransPsrtAdapt = new ListleltransPsrtAdapt(ListLelTransaksi.this,response.body().getSuccess());
                listleltransPsrtAdapt.notifyDataSetChanged();
                listleltransrview.setAdapter(listleltransPsrtAdapt);
                Toast.makeText(ListLelTransaksi.this, "psrtget", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetleltransbypsrtResponse> call, Throwable t) {
            }
        });
    }




}