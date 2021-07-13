package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;

public class AboutsignupActivity extends AppCompatActivity {

    private Button btnbackAbSignup, btnnextAbsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutsignup);

        btnbackAbSignup = (Button)findViewById(R.id.backbtn_absignup);
        btnnextAbsignup = (Button)findViewById(R.id.nextbtn_absignup);

        btnbackAbSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAbSignup();
            }
        });

        btnnextAbsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAbSignup();
            }
        });

    }

    private void nextAbSignup(){
        Intent nextabsignup = new Intent(AboutsignupActivity.this,AboutthreeActivity.class);
        startActivity(nextabsignup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void backAbSignup(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}