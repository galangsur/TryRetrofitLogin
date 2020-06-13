package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.postresponse.addlelpesertamanager.AddlelpesertamanagerResponse;
import com.example.tryretrofitlogin.responses.deletereqlel.DeletereqlelResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyid.GetreqlelbyidResponse;
import com.example.tryretrofitlogin.responses.getuserbyid.GetusernamebyidResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailReqlelActivity extends AppCompatActivity {

    private String reqlelid,userid,namauser,namapendaftar;
    private String lelcomment, lelhewan, lelpelelang, lelharga;
    private TextView tmpReqlelid, tmpReqiduser, tmpReqidpendaftar,tmpReqlelidlelang;
    private TextView txtLelang, txtUser, txtPendaftar;
    private Button btnReqaccept, btnReqreject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reqlel);

        txtLelang = (TextView) findViewById(R.id.txt_reqlelid);
        txtUser = (TextView) findViewById(R.id.txt_reqleluser);
        txtPendaftar = (TextView) findViewById(R.id.txt_reqlelpengirim);
        tmpReqlelid = (TextView) findViewById(R.id.tmp_reqlelid);
        tmpReqiduser = (TextView) findViewById(R.id.tmp_reqlel_iduser);
        tmpReqidpendaftar = (TextView) findViewById(R.id.tmp_reqlel_idpendaftar);
        tmpReqlelidlelang = (TextView) findViewById(R.id.tmp_reqlel_idlelang);
        btnReqaccept = (Button) findViewById(R.id.btn_reqlelaccept);
        btnReqreject = (Button) findViewById(R.id.btn_reqlelreject);

        Intent reqintent = getIntent();
        reqlelid = reqintent.getStringExtra("reqlelid");
        tmpReqlelid.setText(reqlelid);
        Toast.makeText(this, "reqid" + reqlelid, Toast.LENGTH_SHORT).show();

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpReqiduser.setText(userid);
        namauser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtUser.setText(namauser);
        getReqlel();

        btnReqaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptReq();
            }
        });

        btnReqreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getReqlel(){
        String reqlelkey = tmpReqlelid.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetreqlelbyidResponse> call = service.getReqlelbyid(reqlelkey);

        call.enqueue(new Callback<GetreqlelbyidResponse>() {
            @Override
            public void onResponse(Call<GetreqlelbyidResponse> call, Response<GetreqlelbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpReqlelidlelang.setText(response.body().getSuccess().getLelangId());
                    tmpReqidpendaftar.setText(response.body().getSuccess().getPengirimId());
                    getNamauser();
                    getCommentlelang();
                }
            }

            @Override
            public void onFailure(Call<GetreqlelbyidResponse> call, Throwable t) {

            }
        });

    }

    private void getNamauser(){
        String pendaftarkey = tmpReqidpendaftar.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetusernamebyidResponse> call = service.getUsername(
                pendaftarkey,namapendaftar);

        call.enqueue(new Callback<GetusernamebyidResponse>() {
            @Override
            public void onResponse(Call<GetusernamebyidResponse> call, Response<GetusernamebyidResponse> response) {
                if (response.isSuccessful()){
                    txtPendaftar.setText(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<GetusernamebyidResponse> call, Throwable t) {

            }
        });
    }

    private void getCommentlelang(){
        String lkey = tmpReqlelidlelang.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyidResponse> call = service.getdetlelang(
                lkey,lelcomment, lelhewan, lelpelelang, lelharga);

        call.enqueue(new Callback<GetlelangbyidResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyidResponse> call, Response<GetlelangbyidResponse> response) {
                if (response.isSuccessful()){
                    txtLelang.setText(response.body().getSuccess().getComment());
                }
            }

            @Override
            public void onFailure(Call<GetlelangbyidResponse> call, Throwable t) {

            }
        });
    }

    private void acceptReq(){
        String lelkey = tmpReqlelidlelang.getText().toString().trim();
        String pesertakey = tmpReqidpendaftar.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddlelpesertamanagerResponse> call = service.accPeserta(
                pesertakey,lelkey
        );

        call.enqueue(new Callback<AddlelpesertamanagerResponse>() {
            @Override
            public void onResponse(Call<AddlelpesertamanagerResponse> call, Response<AddlelpesertamanagerResponse> response) {
                if (response.isSuccessful()){
                    deleteReq();
                    toMain();
                }
            }

            @Override
            public void onFailure(Call<AddlelpesertamanagerResponse> call, Throwable t) {

            }
        });
    }

    private void rejectReq(){}

    private void deleteReq(){
        String reqkey = tmpReqlelid.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call <DeletereqlelResponse> call = service.deleteReqlel(reqkey);

        call.enqueue(new Callback<DeletereqlelResponse>() {
            @Override
            public void onResponse(Call<DeletereqlelResponse> call, Response<DeletereqlelResponse> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<DeletereqlelResponse> call, Throwable t) {

            }
        });
    }

    private void toMain(){
        Intent intent = new Intent(DetailReqlelActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
