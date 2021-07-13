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
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomepelelangActivity extends AppCompatActivity {

    private Button btntopembeli, btnwalletpelelang,btnpengajuanlelang, btnreqlelang;
    private String username_pelelang, useremail_pelelang, userid_pelelang, usersaldo_pelelang;
    private TextView txtusernamepl, txtuseremailpl, txthomesaldopl;
    private int saldobeforeformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepelelang);

        txtusernamepl = (TextView) findViewById(R.id.txt_usernamepl);
        txtuseremailpl = (TextView) findViewById(R.id.txt_useremailpl);
        txthomesaldopl = (TextView) findViewById(R.id.txt_homesaldopl);
        btntopembeli = (Button) findViewById(R.id.btn_switchroletopembeli);
        btnwalletpelelang = (Button) findViewById(R.id.btn_towalletpelelang);
        btnpengajuanlelang = (Button) findViewById(R.id.btn_pengajuanlelang);
        btnreqlelang = (Button) findViewById(R.id.btn_toreqlel);


        userid_pelelang = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        username_pelelang = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        useremail_pelelang = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getEmail();

        txtusernamepl.setText(username_pelelang);
        txtuseremailpl.setText(useremail_pelelang);

        btntopembeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toswitchrolePembeli();
            }
        });
        btnreqlelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReqlelang();
            }
        });
        btnpengajuanlelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPengajuanlelang();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeGetsaldo();
    }

    private void homeGetsaldo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetWalletInfoResponse> call = service.getinfosaldo(userid_pelelang,usersaldo_pelelang);

        call.enqueue(new Callback<GetWalletInfoResponse>() {
            @Override
            public void onResponse(Call<GetWalletInfoResponse> call, Response<GetWalletInfoResponse> response) {

                if(response.isSuccessful()){
                    saldobeforeformat = response.body().getSaldo();
                } else{
                    Toast.makeText(getApplicationContext(), "Belum memiliki wallet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomepelelangActivity.this, WalletCreateActivity.class);
                    intent.putExtra("username", username_pelelang);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
//                txthomesaldo.setText(response.body().getSaldo());

                //ubahformat Rp.
                Locale localID = new Locale("in","ID");

                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                txthomesaldopl.setText(formatRupiah.format((double)saldobeforeformat));


//                if (response.body() !=null) {
//                    Toast.makeText(HomeActivity.this, "idwallet" + response.body().getUserId()+
//                            "wallet" + response.body().getSaldo(), Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Invalid get wallet info", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(HomeActivity.this, WalletCreateActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                }
            }

            @Override
            public void onFailure(Call<GetWalletInfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void toswitchrolePembeli(){
        Intent intent = new Intent(HomepelelangActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void toReqlelang(){
        Intent intent = new Intent(HomepelelangActivity.this, LelreqlistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void toPengajuanlelang(){
        Intent intent = new Intent(HomepelelangActivity.this, AddLelangActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}