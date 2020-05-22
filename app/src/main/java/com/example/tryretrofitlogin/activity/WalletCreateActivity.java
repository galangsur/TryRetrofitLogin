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
import com.example.tryretrofitlogin.activity.auth.SignupActivity;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.models.Wallet;
import com.example.tryretrofitlogin.responses.getuserbyname.GetuseridResponse;
import com.example.tryretrofitlogin.responses.newwallet.WalletResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletCreateActivity extends AppCompatActivity {

    private Button btncreatewallet;
    private TextView namauser,iduser;
    private String username;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_create);

        btncreatewallet = (Button) findViewById(R.id.btn_createwallet);
        namauser = (TextView) findViewById(R.id.txt_namauser);
        iduser = (TextView) findViewById(R.id.txt_iduser);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        namauser.setText(username);
        Toast.makeText(this, "user " +username, Toast.LENGTH_SHORT).show();
        getUseridbyname();


        btncreatewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WalletCreateActivity.this, "id :" +iduser.getText().toString() , Toast.LENGTH_SHORT).show();
                createwallet();
            }
        });
    }

    private void createwallet(){
        String keyid = iduser.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Wallet wallet = new Wallet(keyid);

        Call<WalletResponse> call = service.createWallet(keyid);

        call.enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                Intent intent = new Intent(WalletCreateActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {

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
                    Toast.makeText(WalletCreateActivity.this, "respn" + response.body().getId(), Toast.LENGTH_SHORT).show();
                }
                Log.i("id", "idgan: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<GetuseridResponse> call, Throwable t) {

            }
        });
    }
}
