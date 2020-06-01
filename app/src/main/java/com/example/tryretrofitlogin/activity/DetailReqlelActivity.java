package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.helper.SharedPrefManager;

public class DetailReqlelActivity extends AppCompatActivity {

    private String reqlelid,userid,username;
    private TextView tmpReqlelid, tmpReqiduser, tmpReqidpendaftar;
    private TextView txtLelang, txtUser, txtPendaftar;
    private Button btnReqaccept, btnReqreject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reqlel);

        txtLelang = (TextView) findViewById(R.id.txt_reqlelid);
        txtUser = (TextView) findViewById(R.id.txt_reqleluser);
        txtPendaftar = (TextView) findViewById(R.id.txt_reqlelpengirim);
        tmpReqlelid = (TextView) findViewById(R.id.tmp_reqlelid);
        tmpReqiduser = (TextView) findViewById(R.id.tmp_reqlel_iduser);
        tmpReqidpendaftar = (TextView) findViewById(R.id.tmp_reqlel_idpendaftar);
        btnReqaccept = (Button) findViewById(R.id.btn_reqlelaccept);
        btnReqreject = (Button) findViewById(R.id.btn_reqlelreject);

        Intent reqlelintent = getIntent();
        reqlelid = reqlelintent.getStringExtra("reqlelid");
        tmpReqlelid.setText(reqlelid);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpReqiduser.setText(userid);
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtUser.setText(username);
    }

    private void getReqlel(){
        String reqlelkey = tmpReqlelid.getText().toString().trim();
    }
}
