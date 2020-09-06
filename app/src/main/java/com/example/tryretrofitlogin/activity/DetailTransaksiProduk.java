package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.putresponse.putleltransstat.UpdateleltransstatResponse;
import com.example.tryretrofitlogin.responses.getleltransbyid.GetleltransbyidResponse;
import com.example.tryretrofitlogin.responses.getprdktransbyid.GetprdktransbyidResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailTransaksiProduk extends AppCompatActivity {
    private String idtransprdk,userid;
    private TextView tmpiddettransprdk,tmpoldstatidprdk,tmpoldstatprdk,tmpnewstatidprdk,tmpnewstatprdk;
    private TextView tmpidusernow,tmpidpmbl,tmpidpnjl;
    private Button submittransprdk;
    private ImageView btnbackprdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransaksi_produk);

        tmpiddettransprdk = (TextView)findViewById(R.id.txtiddettransprdk);
        tmpoldstatidprdk = (TextView)findViewById(R.id.txtidstatoldprdk);
        tmpoldstatprdk = (TextView)findViewById(R.id.txtstatoldprdk);
        tmpnewstatidprdk = (TextView)findViewById(R.id.txtidstatnewprdk);
        tmpnewstatprdk = (TextView) findViewById(R.id.txtstatnewprdk);
        tmpidpnjl = (TextView)findViewById(R.id.reslt_pnjlId);
        tmpidpmbl = (TextView)findViewById(R.id.reslt_pmblId);
        tmpidusernow = (TextView)findViewById(R.id.reslt_usernowprdk);
        submittransprdk = (Button)findViewById(R.id.prdktrans_submit);
        btnbackprdk = (ImageView) findViewById(R.id.btn_backdettransprdk);

        Intent prdkintent = getIntent();
        idtransprdk = prdkintent.getStringExtra("idtransprdk");
        tmpiddettransprdk.setText(idtransprdk);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpidusernow.setText(userid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void getprdktransbyid(){
        String prdktranskey = tmpiddettransprdk.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetprdktransbyidResponse> call = service.prdktransbyid(
                prdktranskey
        );

        call.enqueue(new Callback<GetprdktransbyidResponse>() {
            @Override
            public void onResponse(Call<GetprdktransbyidResponse> call, Response<GetprdktransbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpoldstatidprdk.setText(response.body().getSuccess().getStatushasilId());
                    tmpoldstatprdk.setText(response.body().getSuccess().getStatushasil());
                    tmpidpmbl.setText(response.body().getSuccess().getPembeliId());
                    tmpidpnjl.setText(response.body().getSuccess().getPenjualId());
                    setnewstatidprdk();
                    setnewstatprdk();
                    btnsettingprdk();
                }
            }

            @Override
            public void onFailure(Call<GetprdktransbyidResponse> call, Throwable t) {

            }
        });
    }

    private void setnewstatidprdk(){
        if (tmpoldstatidprdk.getText().toString().equals("1")){
            tmpnewstatidprdk.setText("2");
            submittransprdk.setText("Submit Bayar");//pelelang dan penjual tidak bisa
        }else if (tmpoldstatidprdk.getText().toString().equals("2")){
            tmpnewstatidprdk.setText("3");
            submittransprdk.setText("Submit Pengiriman");//pembeli dan peserta tidak bisa
        }else if (tmpoldstatidprdk.getText().toString().equals("3")){
            tmpnewstatidprdk.setText("4");
            submittransprdk.setText("Submit Penerimaan");//pelelang dan penjual tidak bisa
        }
    }

    private void btnsettingprdk(){
        if (tmpidpnjl.getText().toString().equals(userid) && (tmpoldstatidprdk.getText().toString().equals("1"))){
            submittransprdk.setVisibility(View.INVISIBLE);
        }else if (tmpidpmbl.getText().toString().equals(userid) && (tmpoldstatidprdk.getText().toString().equals("2"))){
            submittransprdk.setVisibility(View.INVISIBLE);
        }else if (tmpidpnjl.getText().toString().equals(userid) && (tmpoldstatidprdk.getText().toString().equals("3"))){
            submittransprdk.setVisibility(View.INVISIBLE);
        }
    }

    private void setnewstatprdk(){
        if (tmpoldstatprdk.getText().toString().equals("a")){
            tmpnewstatprdk.setText("b");
        }else if (tmpoldstatprdk.getText().toString().equals("b")){
            tmpnewstatprdk.setText("c");
        }else if (tmpoldstatprdk.getText().toString().equals("c")){
            tmpnewstatprdk.setText("d");
        }
    }

    private void btnupdatetransprdk(){

        String iddettranskey = tmpiddettransprdk.getText().toString().trim();
        String newstatidkey = tmpnewstatidprdk.getText().toString().trim();
        String newstatkey = tmpnewstatprdk.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<UpdateleltransstatResponse> call = service.ubahstattrans(
                iddettranskey,
                newstatidkey,
                newstatkey
        );

        call.enqueue(new Callback<UpdateleltransstatResponse>() {
            @Override
            public void onResponse(Call<UpdateleltransstatResponse> call, Response<UpdateleltransstatResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailTransaksiProduk.this, response.body().getSuccess().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateleltransstatResponse> call, Throwable t) {

            }
        });
    }
}