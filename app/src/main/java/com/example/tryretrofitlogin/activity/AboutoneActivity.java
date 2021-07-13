package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SigninActivity;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;

public class AboutoneActivity extends AppCompatActivity {

    private Button buttonbackAbone, buttonnextAbone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutone);

        buttonbackAbone = (Button) findViewById(R.id.backbtn_ab1);
        buttonnextAbone = (Button) findViewById(R.id.nextbtn_ab1);

        buttonbackAbone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
            }
        });

        buttonnextAbone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextAbone();
            }
        });
    }


    private void toNextAbone(){
        Intent sigup = new Intent(AboutoneActivity.this, AbouttwoActivity.class);
        startActivity(sigup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void toMain(){
        Intent sigin = new Intent(AboutoneActivity.this, MainActivity.class);
        startActivity(sigin);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        AboutoneActivity.super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}