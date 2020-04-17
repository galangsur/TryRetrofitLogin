package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.helper.SharedPrefManager;

public class HomeActivity extends AppCompatActivity {

    private TextView txtusername, txtuseremail;
    private Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtusername = (TextView) findViewById(R.id.txt_username);
        txtuseremail = (TextView) findViewById(R.id.txt_useremail);
        btnlogout = (Button) findViewById(R.id.btn_logout);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });
    }

    private void userLogout(){
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
