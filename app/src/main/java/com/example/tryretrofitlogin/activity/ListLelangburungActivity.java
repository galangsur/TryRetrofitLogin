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
import com.example.tryretrofitlogin.adapter.Lelbyhwburungadapt;
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

public class ListLelangburungActivity extends AppCompatActivity {
    private RecyclerView lelburungrview;
    private TextView lelburungFilterkey, txtuseridburung;
    private String burungid,userid;
    private ImageView btnAddlel,btnBack;
    private Button btntosignup_lelburung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelangburung);

        lelburungrview = (RecyclerView) findViewById(R.id.lelangburungRV);
        lelburungFilterkey = (TextView)findViewById(R.id.lelburung_filterkey);
        txtuseridburung = (TextView)findViewById(R.id.txtuserIdburung);
        btnAddlel = (ImageView)findViewById(R.id.btn_pengajuanlel);
        btnBack = (ImageView)findViewById(R.id.btn_backreqlist);
        btntosignup_lelburung = (Button)findViewById(R.id.tosignup_lelburung);

        Intent intenthewan = getIntent();
        burungid = intenthewan.getStringExtra("burungid");
        userid = intenthewan.getStringExtra("burunguserid");
        lelburungFilterkey.setText(burungid);
        txtuseridburung.setText(userid);
//        Toast.makeText(this, hewanid, Toast.LENGTH_SHORT).show();

        if (txtuseridburung.getText().toString().equals("")) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ListLelangburungActivity.this, PilihanlelActivity.class);
            btnAddlel.setVisibility(View.INVISIBLE);
            btntosignup_lelburung.setVisibility(View.VISIBLE);
        }else {
            btnAddlel.setVisibility(View.VISIBLE);
            btntosignup_lelburung.setVisibility(View.INVISIBLE);
        }


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        lelburungrview.setLayoutManager(layoutManager);
        lelburungrview.setHasFixedSize(true);

        GetlelangBurung();
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

        btntosignup_lelburung.setOnClickListener(new View.OnClickListener() {
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

    private void GetlelangBurung() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetLelangbyHewanResponse> call = service.getLelbyhewan(
                lelburungFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetLelangbyHewanResponse>() {
            @Override
            public void onResponse(Call<GetLelangbyHewanResponse> call, Response<GetLelangbyHewanResponse> response) {
                Lelbyhwburungadapt reqbyhwburung = new Lelbyhwburungadapt(ListLelangburungActivity.this,response.body().getSuccess());
                reqbyhwburung.notifyDataSetChanged();
                lelburungrview.setAdapter(reqbyhwburung);
            }

            @Override
            public void onFailure(Call<GetLelangbyHewanResponse> call, Throwable t) {

            }
        });
    }

    private void toAddlelang(){
        Intent intent = new Intent(ListLelangburungActivity.this, HomepelelangActivity.class);
        startActivity(intent);
    }

    private void toAboutSignup(){
        Intent toAbSign = new Intent(ListLelangburungActivity.this,AboutoneActivity.class);
        startActivity(toAbSign);
    }

}