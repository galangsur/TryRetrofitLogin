package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Reqlelang;
import com.example.tryretrofitlogin.postresponse.addrequestlelang.AddreqlelangResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getuserbyid.GetusernamebyidResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLelangActivity extends AppCompatActivity {

    private String lelangid, inviscond;
    private String lelcomment, lelhewan, lelpelelang, lelharga, namahewan, namauser, userid;
    private TextView txtComment, txtHewan, txtPemilik, txtHarga;
    private TextView tmpidlelang, tmpidhewan, tmpidpelelang, tmpiduser;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang);

        txtComment = (TextView) findViewById(R.id.txt_leldetcomment);
        txtHewan = (TextView) findViewById(R.id.txt_leldethewan);
        txtPemilik = (TextView) findViewById(R.id.txt_leldetnama);
        txtHarga = (TextView) findViewById(R.id.txt_leldetharga);
        tmpidlelang = (TextView) findViewById(R.id.tmp_idlelang);
        tmpidhewan = (TextView) findViewById(R.id.tmp_idhewan);
        tmpidpelelang = (TextView) findViewById(R.id.tmp_idpelelang);
        tmpiduser = (TextView) findViewById(R.id.tmp_iduser);
        btnDaftar = (Button) findViewById(R.id.btn_daftarlel);
        btnDaftar.setVisibility(View.VISIBLE);

        Intent lelintent = getIntent();
        lelangid = lelintent.getStringExtra("lelid");
        tmpidlelang.setText(lelangid);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpiduser.setText(userid);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getLelang();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarLelang();
                Toast.makeText(DetailLelangActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getLelang(){
        String lkey = tmpidlelang.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyidResponse> call = service.getdetlelang(
                lkey, lelcomment, lelhewan, lelpelelang, lelharga);

        call.enqueue(new Callback<GetlelangbyidResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyidResponse> call, Response<GetlelangbyidResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailLelangActivity.this, "res" +response.body().getSuccess().toString(), Toast.LENGTH_SHORT).show();
                    txtComment.setText(response.body().getSuccess().getComment());
                    tmpidhewan.setText(response.body().getSuccess().getHewanId());
                    tmpidpelelang.setText(response.body().getSuccess().getUserId());
                    txtHarga.setText(response.body().getSuccess().getHarga());
                    getNamaHewan();
                    getNamaUser();

                    inviscond = tmpidpelelang.getText().toString().trim();
                    if (userid.equals(inviscond)){
                        Toast.makeText(DetailLelangActivity.this, "pemilik lelang", Toast.LENGTH_SHORT).show();
                        btnDaftar.setVisibility(View.INVISIBLE);
                    }else {
                        Toast.makeText(DetailLelangActivity.this, "pendaftar", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetlelangbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getNamaHewan(){
        String hkey = tmpidhewan.getText().toString().trim();
        Log.d("hkey", hkey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GethewanbyidResponse> call = service.gethewanjenis(
                hkey, namahewan
        );

        call.enqueue(new Callback<GethewanbyidResponse>() {
            @Override
            public void onResponse(Call<GethewanbyidResponse> call, Response<GethewanbyidResponse> response) {
                if (response.isSuccessful()){
                    txtHewan.setText(response.body().getSuccess().getJenis());
                }
            }

            @Override
            public void onFailure(Call<GethewanbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getNamaUser(){
        String ukey = tmpidpelelang.getText().toString().trim();
        Log.d("ukey", ukey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetusernamebyidResponse> call = service.getUsername(
                ukey, namauser
        );

        call.enqueue(new Callback<GetusernamebyidResponse>() {
            @Override
            public void onResponse(Call<GetusernamebyidResponse> call, Response<GetusernamebyidResponse> response) {
                if (response.isSuccessful()){
                    txtPemilik.setText(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<GetusernamebyidResponse> call, Throwable t) {

            }
        });
    }

    private void daftarLelang(){
        String pky = tmpidpelelang.getText().toString().trim();
        Log.d("pky", pky);
        String lky = tmpidlelang.getText().toString().trim();
        Log.d("lky", lky);
        String uky = tmpiduser.getText().toString().trim();
        Log.d("uky", uky);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Reqlelang reqlelang = new Reqlelang(uky,pky,lky);

        Call<AddreqlelangResponse> call = service.reqLelang(
                uky, pky, lky
        );

        call.enqueue(new Callback<AddreqlelangResponse>() {
            @Override
            public void onResponse(Call<AddreqlelangResponse> call, Response<AddreqlelangResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailLelangActivity.this, "toast" +response.body().getSuccess().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddreqlelangResponse> call, Throwable t) {

            }
        });
    }
}
