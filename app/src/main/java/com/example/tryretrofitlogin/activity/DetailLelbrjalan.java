package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;

public class DetailLelbrjalan extends AppCompatActivity {
    private String lelbrjalanid;
    private TextView tmpgctoken,tmpidlelbrjalan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelbrjalan);

            tmpgctoken = (TextView)findViewById(R.id.gctoken);
            tmpidlelbrjalan = (TextView)findViewById(R.id.id_lelbrjalan);

        Intent lelintent = getIntent();
        lelbrjalanid = lelintent.getStringExtra("lelid");
        tmpidlelbrjalan.setText(lelbrjalanid);
    }

    private void getLelbrjalan(){

    }
}