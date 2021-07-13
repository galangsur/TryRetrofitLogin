package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SigninActivity;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;

public class AbouttwoActivity extends AppCompatActivity {

    private Button buttonbackAbtwo, buttonnextAbtwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abouttwo);

        buttonbackAbtwo = (Button) findViewById(R.id.backbtn_ab2);
        buttonnextAbtwo = (Button) findViewById(R.id.nextbtn_ab2);

        buttonbackAbtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAbone();
            }
        });

        buttonnextAbtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextAbtwo();
            }
        });
    }

    private void toAbone(){
        Intent sigin = new Intent(AbouttwoActivity.this, AboutoneActivity.class);
        startActivity(sigin);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    private void toNextAbtwo(){
        Intent sigup = new Intent(AbouttwoActivity.this, AboutthreeActivity.class);
        startActivity(sigup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        AbouttwoActivity.super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}