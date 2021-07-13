package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tryretrofitlogin.R;

public class PilihanlelActivity extends AppCompatActivity {

    private Button btnayaduan,btncupang,btnburung;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihanlel);

        btnayaduan = (Button) findViewById(R.id.btnlelayaduan);
        btncupang = (Button) findViewById(R.id.btnlelcupang);
        btnburung = (Button) findViewById(R.id.btnlelburung);

        Intent intentpilihanhewan = getIntent();
        userid = intentpilihanhewan.getStringExtra("userid");

        btnayaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentaya = new Intent(PilihanlelActivity.this, ListLelangayamActivity.class);
                intentaya.putExtra("ayamid", "1");
                intentaya.putExtra("ayamuserid", userid);
                startActivity(intentaya);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        btncupang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcup = new Intent(PilihanlelActivity.this, ListLelangcupangActivity.class);
                intentcup.putExtra("cupangid", "2");
                intentcup.putExtra("cupanguserid", userid);
                startActivity(intentcup);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        btnburung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbur = new Intent(PilihanlelActivity.this, ListLelangburungActivity.class);
                intentbur.putExtra("burungid", "3");
                intentbur.putExtra("burunguserid", userid);
                startActivity(intentbur);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}