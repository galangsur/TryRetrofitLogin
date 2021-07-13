package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Lelbyhwcupangadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.GetLelangbyHewanResponse;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangcupangActivity extends AppCompatActivity {
    private RecyclerView lelcupangrview;
    private TextView lelcupangFilterkey, txtuseridcupang;
    private String cupangid,userid;
    private ImageView btnAddlel, btnBack;
    private Button btntosignup_lelcupang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelangcupang);

        lelcupangrview = (RecyclerView) findViewById(R.id.lelangsapiRV);
        lelcupangFilterkey = (TextView)findViewById(R.id.lelsapi_filterkey);
        txtuseridcupang = (TextView)findViewById(R.id.txtuserIdcupang);
        btnAddlel = (ImageView)findViewById(R.id.btn_pengajuanlel);
        btnBack = (ImageView)findViewById(R.id.btn_backreqlist);
        btntosignup_lelcupang = (Button)findViewById(R.id.tosignup_lelcupang);

        Intent intenthewan = getIntent();
        cupangid = intenthewan.getStringExtra("cupangid");
        userid = intenthewan.getStringExtra("cupanguserid");
        lelcupangFilterkey.setText(cupangid);
        txtuseridcupang.setText(userid);
//        Toast.makeText(this, lelsapiFilterkey.getText().toString(), Toast.LENGTH_SHORT).show();

        if (txtuseridcupang.getText().toString().equals("")) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ListLelangcupangActivity.this, PilihanlelActivity.class);
            btnAddlel.setVisibility(View.INVISIBLE);
            btntosignup_lelcupang.setVisibility(View.VISIBLE);
        }else {
            btnAddlel.setVisibility(View.VISIBLE);
            btntosignup_lelcupang.setVisibility(View.INVISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        lelcupangrview.setLayoutManager(layoutManager);
        lelcupangrview.setHasFixedSize(true);

        GetlelangCupang();
        btnAddlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddlelang();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btntosignup_lelcupang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAboutSignup();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void GetlelangCupang(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetLelangbyHewanResponse> call = service.getLelbyhewan(
                lelcupangFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetLelangbyHewanResponse>() {
            @Override
            public void onResponse(Call<GetLelangbyHewanResponse> call, Response<GetLelangbyHewanResponse> response) {
                Lelbyhwcupangadapt reqbyhwcupang = new Lelbyhwcupangadapt(ListLelangcupangActivity.this,response.body().getSuccess());
                reqbyhwcupang.notifyDataSetChanged();
                lelcupangrview.setAdapter(reqbyhwcupang);
            }

            @Override
            public void onFailure(Call<GetLelangbyHewanResponse> call, Throwable t) {

            }
        });
    }

    private void toAddlelang(){
        Intent intent = new Intent(ListLelangcupangActivity.this, HomepelelangActivity.class);
        startActivity(intent);
    }

    private void toAboutSignup(){
        Intent toAbSign = new Intent(ListLelangcupangActivity.this,AboutoneActivity.class);
        startActivity(toAbSign);
    }

}