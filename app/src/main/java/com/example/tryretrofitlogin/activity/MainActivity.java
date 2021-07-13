package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SigninActivity;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonSignIn, buttonToLelang;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//        ip= Formatter.formatIpAddress(manager.getConnectionInfo().getIpAddress());
//        Toast.makeText(this, "hiya" + ip, Toast.LENGTH_SHORT).show();

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonToLelang = (Button) findViewById(R.id.buttontoLelang);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignin();
            }
        });

        buttonToLelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLihatlelangasguest();
            }
        });
    }

    private void toSignin(){
        Intent sigin = new Intent(MainActivity.this, SigninActivity.class);
        startActivity(sigin);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    private void toLihatlelangasguest(){
        Intent lihatlelangasguest = new Intent(MainActivity.this, PilihanlelActivity.class);
        startActivity(lihatlelangasguest);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void tryrating(){
        Intent sigup = new Intent(MainActivity.this, RatingnreviewinputActivity.class);
        startActivity(sigup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }



    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
