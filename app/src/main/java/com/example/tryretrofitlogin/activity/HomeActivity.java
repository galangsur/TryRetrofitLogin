package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.helper.SharedPrefManager;

public class HomeActivity extends AppCompatActivity {

    private TextView txtusername, txtuseremail;
    private Button btnlogout, btnwallet, btntolelang, btntolistlel, btntoreqlel;
    private String username, useremail;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtusername = (TextView) findViewById(R.id.txt_username);
        txtuseremail = (TextView) findViewById(R.id.txt_useremail);
        btnlogout = (Button) findViewById(R.id.btn_logout);
        btnwallet = (Button) findViewById(R.id.btn_towallet);
        btntolelang = (Button) findViewById(R.id.btn_toaddlelang);
        btntolistlel = (Button) findViewById(R.id.btn_tolistlelang);
        btntoreqlel = (Button) findViewById(R.id.btn_toreqlel);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        useremail = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getEmail();

        txtusername.setText(username);
        txtuseremail.setText(useremail);

        btnlogout.setOnClickListener(new View.OnClickListener() {
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

        btntolelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddlelang();
            }
        });

        btntolistlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toListlelang();
            }
        });

        btntoreqlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReqlelang();
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

    private void toAddlelang(){
        Intent intent = new Intent(HomeActivity.this, AddLelangActivity.class);
        startActivity(intent);
    }

    private void toListlelang(){
        Intent intent = new Intent(HomeActivity.this, ListLelangActivity.class);
        startActivity(intent);
    }

    private void toReqlelang(){
        Intent intent = new Intent(HomeActivity.this, LelreqlistActivity.class);
        startActivity(intent);
    }
}
