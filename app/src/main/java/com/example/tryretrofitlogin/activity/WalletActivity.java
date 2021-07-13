package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.auth.SignupActivity;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.postresponse.addreqtopup.AddtopupreqResponse;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.example.tryretrofitlogin.responses.topupwallet.TopupResponse;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletActivity extends AppCompatActivity {

    private TextView txtuserid, txtusersaldo, txtusernama;
    private EditText saldotmbh;
    private Button btntambahsaldo, btntopup_a, btntopup_b, btntopup_c;
    private ImageView btn_back;
    private String usersaldo;
    private String a,b,c;
    private String userid,usernama;
    private int saldoWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        txtuserid = (TextView) findViewById(R.id.txt_userid);
        txtusersaldo = (TextView) findViewById(R.id.txt_saldoawal);
        txtusernama = (TextView) findViewById(R.id.tmpwlt_namauser);
        saldotmbh = (EditText) findViewById(R.id.edtxt_saldotambah);
        btntambahsaldo = (Button) findViewById(R.id.btn_tambahsaldo);
        btntopup_a = (Button)findViewById(R.id.btntmbh_a);
        btntopup_b = (Button)findViewById(R.id.btntmbh_b);
        btntopup_c = (Button)findViewById(R.id.btntmbh_c);
        btn_back =  (ImageView)findViewById(R.id.btn_backtohome);
        a = "500000";
        b = "1000000";
        c = "1500000";

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        usernama = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        txtuserid.setText(userid);
        txtusernama.setText(usernama);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btntambahsaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fungsitambah();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "iser" + userid, Toast.LENGTH_SHORT).show();
        getSaldo();

        btntopup_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldotmbh.setText(a);
            }
        });
        btntopup_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldotmbh.setText(b);
            }
        });
        btntopup_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldotmbh.setText(c);
            }
        });
    }

    private void fungsitambah(){
        String user_id = txtuserid.getText().toString().trim();
        String user_nama = txtusernama.getText().toString().trim();
//        String nominal = saldotmbh.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddtopupreqResponse> call = service.createTopupreq(
                user_id ,user_id, user_nama, saldotmbh.getText().toString());

        call.enqueue(new Callback<AddtopupreqResponse>() {
            @Override
            public void onResponse(Call<AddtopupreqResponse> call, Response<AddtopupreqResponse> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(WalletActivity.this, "yea", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }

            @Override
            public void onFailure(Call<AddtopupreqResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getSaldo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetWalletInfoResponse> call = service.getinfosaldo(userid,usersaldo);

        call.enqueue(new Callback<GetWalletInfoResponse>() {
            @Override
            public void onResponse(Call<GetWalletInfoResponse> call, Response<GetWalletInfoResponse> response) {

                    saldoWallet = response.body().getSaldo();
//                  txtusersaldo.setText(response.body().getSaldo());

                    //ubahformat Rp.
                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    txtusersaldo.setText(formatRupiah.format((double)saldoWallet));

                    if (response.body() !=null) {
//                        Toast.makeText(WalletActivity.this, "idwallet" + response.body().getUserId()+
//                                "wallet" + response.body().getSaldo(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid get wallet info", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WalletActivity.this, WalletCreateActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }

            @Override
            public void onFailure(Call<GetWalletInfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
