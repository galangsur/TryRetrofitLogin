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
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private TextView txtusername, txtuseremail, txthomesaldo;
    private Button btnwallet, btntolelang, btntolistlel, btntoreqlel,
            btntogroupcht, btntoimgtry, btntopasar,btntrycamera;
    private String username, useremail;
    private String userid,usersaldo;
    private ImageView imglogout,imglelangsapi,imglelangayam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtusername = (TextView) findViewById(R.id.txt_username);
        txtuseremail = (TextView) findViewById(R.id.txt_useremail);
        txthomesaldo = (TextView) findViewById(R.id.txt_homesaldo);
        imglogout = (ImageView) findViewById(R.id.btn_logout);
        imglelangayam = (ImageView) findViewById(R.id.img_lelayam);
        imglelangsapi =  (ImageView) findViewById(R.id.img_lelsapi);
        btnwallet = (Button) findViewById(R.id.btn_towallet);
        btntoreqlel = (Button) findViewById(R.id.btn_toreqlel);
        btntoimgtry = (Button)findViewById(R.id.btn_toimgtry);
        btntopasar = (Button)findViewById(R.id.btn_pasarhewan);
        btntrycamera = (Button)findViewById(R.id.try_camera);
//        btntolelang = (Button) findViewById(R.id.btn_toaddlelang);
//        btntolistlel = (Button) findViewById(R.id.btn_tolistlelang);

//        btntogroupcht = (Button) findViewById(R.id.btn_togroupcht);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        useremail = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getEmail();

        txtusername.setText(username);
        txtuseremail.setText(useremail);
        homeGetsaldo();

        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });
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
        btntopasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPasar();
            }
        });
        btntoimgtry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toImage();
            }
        });
        btntrycamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTrycamera();
            }
        });

        imglelangsapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListLelangsapiActivity.class);
                intent.putExtra("sapiid", "1");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        imglelangayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListLelangayamActivity.class);
                intent.putExtra("ayamid", "2");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(HomeActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Toast.makeText(HomeActivity.this, "token" + newToken, Toast.LENGTH_SHORT).show();
                Log.d("token",newToken);
            }
        });
    }

    private void userLogout(){
        SharedPrefManager.getInstance(this).logout();
        finish();
        Toast.makeText(this, "logoutsucces", Toast.LENGTH_SHORT).show();
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

    private void toImage(){
        Intent intent = new Intent(HomeActivity.this,ImageRetrieveTry.class);
        startActivity(intent);
    }

    private void toPasar(){
        Intent intent = new Intent(HomeActivity.this,ListProdukActivity.class);
        startActivity(intent);
    }

    private void toTrycamera(){
        Intent intent = new Intent(HomeActivity.this,ImageUploadActivity.class);
        startActivity(intent);
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
                txthomesaldo.setText(response.body().getSaldo());
                if (response.body() !=null) {
                    Toast.makeText(HomeActivity.this, "idwallet" + response.body().getUserId()+
                            "wallet" + response.body().getSaldo(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid get wallet info", Toast.LENGTH_LONG).show();
                }
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
