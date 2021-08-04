package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Wallet;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private TextView txtusername, txtuseremail, txthomesaldo;
    private Button btnwallet, btntolelang, btntolistlel, btntoreqlel, btnrolepelelang,
            btntogroupcht, btntoimgtry, btntopasar,btntrycamera,btnlistlelaspeserta, btnlistlelaspelelang;
    private String username, useremail;
    private String userid,usersaldo,saldovalidate;
    private ImageView imglogout,imglelangsapi,imglelangayam,
            imglisttransprdkpnjl,imglisttransprdkpmbl,imglisttranslelpnwr,imglisttranslelpllg;
    private int saldobeforeformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtusername = (TextView) findViewById(R.id.txt_username);
        txtuseremail = (TextView) findViewById(R.id.txt_useremail);
        txthomesaldo = (TextView) findViewById(R.id.txt_homesaldo);
        imglogout = (ImageView) findViewById(R.id.btn_logout);
        btnwallet = (Button) findViewById(R.id.btn_towallet);
        btntoreqlel = (Button) findViewById(R.id.btn_toreqlel);
        btntolelang = (Button)findViewById(R.id.btn_pasarlelang);
        btnrolepelelang = (Button) findViewById(R.id.btn_switchroletopelelang);
        imglisttranslelpnwr = (ImageView)findViewById(R.id.plihantranspnwr);
        btnlistlelaspeserta = (Button)findViewById(R.id.sebagaipeserta);
        btnlistlelaspelelang = (Button)findViewById(R.id.sebagaipelelang);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        useremail = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getEmail();

        txtusername.setText(username);
        txtuseremail.setText(useremail);


        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });

        imglisttranslelpnwr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toListleltrans();
            }
        });
//        imglisttransprdkpnjl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toListprdktrans();
//            }
//        });

        btnwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWallet();
            }
        });
        btntoreqlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReqlelang();
            }
        });
//        btntopasar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toPasar();
//            }
//        });
        btntolelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLelang();
            }
        });
        btnrolepelelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toswitchrolePelelang();
            }
        });

//        btntoimgtry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toImage();
//            }
//        });
//        btntrycamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toTrycamera();
//            }
//        });

        btnlistlelaspeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toListlelaspeserta();
            }
        });
        btnlistlelaspelelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toListlelaspelelang();
            }
        });

//        btntogroupcht.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                togc();
//            }
//        });

//        imglelangsapi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, ListLelangsapiActivity.class);
//                intent.putExtra("sapiid", "1");
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//            }
//        });
//        imglelangayam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, ListLelangayamActivity.class);
//                intent.putExtra("ayamid", "2");
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//            }
//        });

//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(HomeActivity.this, new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String newToken = instanceIdResult.getToken();
////                Toast.makeText(HomeActivity.this, "token" + newToken, Toast.LENGTH_SHORT).show();
//                Log.d("token",newToken);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeGetsaldo();
    }

    private void userLogout(){
        SharedPrefManager.getInstance(this).logout();
        finish();
        Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toWallet(){
        Intent intent = new Intent(HomeActivity.this, WalletActivity.class);
        startActivity(intent);
    }

    private void toReqlelang(){
        Intent intent = new Intent(HomeActivity.this, LelreqlistActivity.class);
        startActivity(intent);
    }

    private void toLelang(){
        Intent intent = new Intent(HomeActivity.this,PilihanlelActivity.class);
        intent.putExtra("userid",userid);
        startActivity(intent);
    }

    private void toListleltrans(){
        Intent intent = new Intent(HomeActivity.this,ListLelTransaksi.class);
        startActivity(intent);
    }

    private void toListlelaspeserta(){
        Intent intent = new Intent(HomeActivity.this,ListLelangAsPeserta.class);
        startActivity(intent);
    }

    private void toListlelaspelelang(){
        Intent intent = new Intent(HomeActivity.this,ListLelangAsPelelang.class);
        startActivity(intent);
    }

    private void toswitchrolePelelang(){
        Intent intent = new Intent(HomeActivity.this, HomepelelangActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }


    private void homeGetsaldo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetWalletInfoResponse> call = service.getinfosaldo(userid,usersaldo);

        call.enqueue(new Callback<GetWalletInfoResponse>() {
            @Override
            public void onResponse(Call<GetWalletInfoResponse> call, Response<GetWalletInfoResponse> response) {

                if(response.isSuccessful()){
                    saldobeforeformat = response.body().getSaldo();
                } else{
                    Toast.makeText(getApplicationContext(), "Belum memiliki wallet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomeActivity.this, WalletCreateActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
//                txthomesaldo.setText(response.body().getSaldo());

                //ubahformat Rp.
                Locale localID = new Locale("in","ID");

                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                txthomesaldo.setText(formatRupiah.format((double)saldobeforeformat));


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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
