package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;

public class DetailLelangActivity extends AppCompatActivity {

    private String lelangid;
    private TextView txtComment, txtJenis, txtPemilik, txtHarga;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang);

        txtComment = (TextView) findViewById(R.id.txt_leldetcomment);
        txtJenis = (TextView) findViewById(R.id.txt_leldetjenis);
        txtPemilik = (TextView) findViewById(R.id.txt_leldetnama);
        txtHarga = (TextView) findViewById(R.id.txt_leldetharga);


        Intent lelintent = getIntent();
        lelangid = lelintent.getStringExtra("lelid");
        Toast.makeText(this, "id : " +lelangid, Toast.LENGTH_SHORT).show();
    }
}
