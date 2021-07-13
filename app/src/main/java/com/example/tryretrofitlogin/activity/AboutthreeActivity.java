package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SigninActivity;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;

public class AboutthreeActivity extends AppCompatActivity {

    private Button buttonbackAbthree, buttonnextAbthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutthree);

        buttonbackAbthree = (Button) findViewById(R.id.backbtn_ab3);
        buttonnextAbthree = (Button) findViewById(R.id.nextbtn_ab3);

        buttonbackAbthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAbtwo();
            }
        });

        buttonnextAbthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextSignup();
            }
        });
    }

    private void toAbtwo(){
        Intent sigin = new Intent(AboutthreeActivity.this, AbouttwoActivity.class);
        startActivity(sigin);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    private void toNextSignup(){
        Intent sigup = new Intent(AboutthreeActivity.this, SignupActivity.class);
        startActivity(sigup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        AboutthreeActivity.super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}