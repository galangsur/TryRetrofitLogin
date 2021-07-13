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
import com.example.tryretrofitlogin.adapter.Lelbyhwayamadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.GetLelangbyHewanResponse;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLelangayamActivity extends AppCompatActivity {
    private RecyclerView lelayamrview;
    private TextView lelayamFilterkey, txtuseridayam;
    private String ayamid,userid;
    private ImageView btnAddlel,btnBack;
    private Button btntosignup_lelayam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lelang_ayam);

        lelayamrview = (RecyclerView) findViewById(R.id.lelangayamRV);
        lelayamFilterkey = (TextView)findViewById(R.id.lelayam_filterkey);
        txtuseridayam = (TextView)findViewById(R.id.txtuserIdayam);
        btnAddlel = (ImageView)findViewById(R.id.btn_pengajuanlel);
        btnBack = (ImageView)findViewById(R.id.btn_backreqlist);
        btntosignup_lelayam = (Button)findViewById(R.id.tosignup_lelayam);

        Intent intenthewan = getIntent();
        ayamid = intenthewan.getStringExtra("ayamid");
        userid = intenthewan.getStringExtra("ayamuserid");
        lelayamFilterkey.setText(ayamid);
        txtuseridayam.setText(userid);
//      Toast.makeText(this, hewanid, Toast.LENGTH_SHORT).show();

        //cara bikin if else dengan nilai sama dengan kosong atau tidak ada
        if (txtuseridayam.getText().toString().equals("")) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ListLelangayamActivity.this, PilihanlelActivity.class);
            btnAddlel.setVisibility(View.INVISIBLE);
            btntosignup_lelayam.setVisibility(View.VISIBLE);
        }else {
            btnAddlel.setVisibility(View.VISIBLE);
            btntosignup_lelayam.setVisibility(View.INVISIBLE);
        }//udah bisa ini bisa lihat lelang tanpa harus puny akun

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        lelayamrview.setLayoutManager(layoutManager);
        lelayamrview.setHasFixedSize(true);

        GetlelangAyam();
        btnAddlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddlelang();
            }
        });

        btntosignup_lelayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAboutSignup();
            }
        });

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

    public void GetlelangAyam(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetLelangbyHewanResponse> call = service.getLelbyhewan(
                lelayamFilterkey.getText().toString().trim());

        call.enqueue(new Callback<GetLelangbyHewanResponse>() {
            @Override
            public void onResponse(Call<GetLelangbyHewanResponse> call, Response<GetLelangbyHewanResponse> response) {
                Lelbyhwayamadapt reqbyhwayam = new Lelbyhwayamadapt(ListLelangayamActivity.this,response.body().getSuccess());
                reqbyhwayam.notifyDataSetChanged();
                lelayamrview.setAdapter(reqbyhwayam);
            }

            @Override
            public void onFailure(Call<GetLelangbyHewanResponse> call, Throwable t) {

            }
        });
    }

    private void toAddlelang(){
        Intent intent = new Intent(ListLelangayamActivity.this, HomepelelangActivity.class);
        startActivity(intent);
    }

    private void toAboutSignup(){
        Intent toAbSign = new Intent(ListLelangayamActivity.this,AboutoneActivity.class);
        startActivity(toAbSign);
    }
}