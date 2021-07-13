package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.models.Wallet;
import com.example.tryretrofitlogin.postresponse.addwallet.AddWalletResponse;
import com.example.tryretrofitlogin.responses.getuserbyname.GetuseridResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletCreateActivity extends AppCompatActivity {

    private Button btncreatewallet;
    private TextView namauser,iduser, nominalawal;
    private String username;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_create);

        btncreatewallet = (Button) findViewById(R.id.btn_createwallet);
        namauser = (TextView) findViewById(R.id.txt_namauser);
        iduser = (TextView) findViewById(R.id.txt_iduser);
        nominalawal = (TextView) findViewById(R.id.txt_nominalawal);
        nominalawal.setText("0");

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        namauser.setText(username);
//        Toast.makeText(this, "user " +username, Toast.LENGTH_SHORT).show();
        getUseridbyname();


        btncreatewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(WalletCreateActivity.this, "id :" +iduser.getText().toString() , Toast.LENGTH_SHORT).show();
                createwallet();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void createwallet(){
        String keyid = iduser.getText().toString().trim();
        String nominalkey = nominalawal.getText().toString().trim();
//        Toast.makeText(this, "kyid" + keyid + nominalkey, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddWalletResponse> call = service.createWallet(keyid,nominalkey);

        call.enqueue(new Callback<AddWalletResponse>() {
            @Override
            public void onResponse(Call<AddWalletResponse> call, Response<AddWalletResponse> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(WalletCreateActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                } else {
                    Toast.makeText(WalletCreateActivity.this, "Wallet gagal membuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddWalletResponse> call, Throwable t) {

            }
        });
    }

    private void getUseridbyname(){
        String key = namauser.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetuseridResponse> call = service.getUserid(key,userid);

        call.enqueue(new Callback<GetuseridResponse>() {
            @Override
            public void onResponse(Call<GetuseridResponse> call, Response<GetuseridResponse> response) {
                iduser.setText(response.body().getId());
                if (response.isSuccessful()){
//                    Toast.makeText(WalletCreateActivity.this, "respn" + response.body().getId(), Toast.LENGTH_SHORT).show();
                }
                Log.i("id", "idgan: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<GetuseridResponse> call, Throwable t) {

            }
        });
    }
}
