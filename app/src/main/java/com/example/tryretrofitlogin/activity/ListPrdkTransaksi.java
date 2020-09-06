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
import com.example.tryretrofitlogin.adapter.ListleltransPllgAdapt;
import com.example.tryretrofitlogin.adapter.ListleltransPsrtAdapt;
import com.example.tryretrofitlogin.adapter.ListtransPmblAdapt;
import com.example.tryretrofitlogin.adapter.ListtransPnjlAdapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getleltransbypllg.GetleltransbypllgResponse;
import com.example.tryretrofitlogin.responses.getleltransbypsrt.GetleltransbypsrtResponse;
import com.example.tryretrofitlogin.responses.getprdktransbypmbl.GetprdktransbypmblResponse;
import com.example.tryretrofitlogin.responses.getprdktransbypnjl.GetprdktransbypnjlResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListPrdkTransaksi extends AppCompatActivity {
    public RecyclerView listlprdktransrview;
    private TextView listprdktransFilter;
    private String userid;
    private ImageView backlistprdktrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prdktransi);

        listlprdktransrview = (RecyclerView) findViewById(R.id.prdktransaksiRV);
        listprdktransFilter = (TextView) findViewById(R.id.prdktrans_filterkey);
        backlistprdktrans = (ImageView) findViewById(R.id.btn_backprdktranslist);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        listprdktransFilter.setText(userid);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        listlprdktransrview.setLayoutManager(layoutManager);
        listlprdktransrview.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPrdktrans();
        backlistprdktrans.setOnClickListener(new View.OnClickListener() {
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

    public void getPrdktrans(){
        final String getpllgkey = listprdktransFilter.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final APIService service = retrofit.create(APIService.class);

        Call<GetprdktransbypnjlResponse> call = service.prdktransbypnjl(
                getpllgkey
        );

        call.enqueue(new Callback<GetprdktransbypnjlResponse>() {
            @Override
            public void onResponse(Call<GetprdktransbypnjlResponse> call, Response<GetprdktransbypnjlResponse> response) {
                if (response.isSuccessful()){
                    ListtransPnjlAdapt listtransPnjlAdapt = new ListtransPnjlAdapt(ListPrdkTransaksi.this,response.body().getSuccess());
                    if (listtransPnjlAdapt.getItemCount() !=0){
                        listtransPnjlAdapt.notifyDataSetChanged();
                        listlprdktransrview.setAdapter(listtransPnjlAdapt);
                        Toast.makeText(ListPrdkTransaksi.this, "pnjlget", Toast.LENGTH_SHORT).show();
                    } else if (response != null){

                        Call<GetprdktransbypmblResponse> call1 = service.prdktransbypmbl(
                                getpllgkey
                        );
                        call1.enqueue(new Callback<GetprdktransbypmblResponse>() {
                            @Override
                            public void onResponse(Call<GetprdktransbypmblResponse> call, Response<GetprdktransbypmblResponse> response) {
                                if (response.isSuccessful()){
                                    ListtransPmblAdapt listtransPmblAdapt = new ListtransPmblAdapt(ListPrdkTransaksi.this,response.body().getSuccess());
                                    listtransPmblAdapt.notifyDataSetChanged();
                                    listlprdktransrview.setAdapter(listtransPmblAdapt);
                                    Toast.makeText(ListPrdkTransaksi.this, "pmblget", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<GetprdktransbypmblResponse> call, Throwable t) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<GetprdktransbypnjlResponse> call, Throwable t) {

            }
        });
    }
}