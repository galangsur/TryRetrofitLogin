package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.example.tryretrofitlogin.responses.topupwallet.TopupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletActivity extends AppCompatActivity {

    private TextView txtuserid, txtusersaldo;
    private EditText saldotmbh;
    private Button btntambahsaldo, btntopup_a, btntopup_b, btntopup_c;
    private ImageView btn_back;
    private String usersaldo;
    private String a,b,c;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        txtuserid = (TextView) findViewById(R.id.txt_userid);
        txtusersaldo = (TextView) findViewById(R.id.txt_saldoawal);
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
        txtuserid.setText(userid);

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
        Toast.makeText(this, "iser" + userid, Toast.LENGTH_SHORT).show();
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
//        String nominal = saldotmbh.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<TopupResponse> call = service.saldotambah(user_id, saldotmbh.getText().toString());

        call.enqueue(new Callback<TopupResponse>() {
            @Override
            public void onResponse(Call<TopupResponse> call, Response<TopupResponse> response) {
                Toast.makeText(getApplicationContext(), response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<TopupResponse> call, Throwable t) {
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
                    txtusersaldo.setText(response.body().getSaldo());
                    if (response.body() !=null) {
                        Toast.makeText(WalletActivity.this, "idwallet" + response.body().getUserId()+
                                "wallet" + response.body().getSaldo(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid get wallet info", Toast.LENGTH_LONG).show();
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
