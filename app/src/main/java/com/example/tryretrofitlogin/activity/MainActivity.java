package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SigninActivity;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonSignIn, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignin();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignup();
            }
        });
    }

    private void toSignin(){
        Intent sigin = new Intent(MainActivity.this, SigninActivity.class);
        startActivity(sigin);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    private void toSignup(){
        Intent sigup = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(sigup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
