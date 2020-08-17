package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.models.Message;
import com.example.tryretrofitlogin.postresponse.addhasillelang.AddHasillelangResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbygc.GetlelbrjalanbygcResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LelangResultActivity extends AppCompatActivity {
    private TextView resltMsgid, resltUserid,  resltPelelangid, resltPelelangname, lelbrjalankey;
    private TextView reslidlelbrjalan, reslidpeserta, statshlel,stathlelid, reslhewanid;
    private EditText resltUsername, resltNilaiakhir, resltHargaawal ;
    private Button resltBtntohome;
    private String messageId,lbid,lbuserid,lbhewanid,lbharga,lbcomment,statushasilawal,statushasilawalid,pesrtaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang_result);

        resltMsgid = (TextView) findViewById(R.id.reslt_messageId);
        resltUserid = (TextView) findViewById(R.id.reslt_userId);
        lelbrjalankey = (TextView) findViewById(R.id.lelbrjalankey);
        resltUsername = (EditText) findViewById(R.id.rslt_namapemenang);
        resltNilaiakhir = (EditText) findViewById(R.id.rslt_nilaiakhir);
        resltHargaawal = (EditText) findViewById(R.id.rslt_hargaawal);
        resltBtntohome = (Button) findViewById(R.id.reslt_tohome);
        resltPelelangid = (TextView) findViewById(R.id.rsltidpelelang);
        reslidlelbrjalan = (TextView) findViewById(R.id.rsltidlelbrjalan);
        reslidpeserta = (TextView) findViewById(R.id.reslt_pesertaId);
        statshlel = (TextView)findViewById(R.id.txtstatshasilawal);
        stathlelid = (TextView)findViewById(R.id.txtstatshasilawalid);
        reslhewanid = (TextView)findViewById(R.id.reslt_hewanid);


        resltUsername.setKeyListener(null);
        resltNilaiakhir.setKeyListener(null);
        resltHargaawal.setKeyListener(null);
        stathlelid.setText("1");
        statshlel.setText("Belum Dibayarkan");

        Intent gcIntent = getIntent();
        messageId = gcIntent.getStringExtra("messageId");
        Toast.makeText(this, "messageid"+ messageId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getresltdetail();


        resltBtntohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addhasillelang();
            }
        });

    }

    private void getresltdetail(){
        DatabaseReference resltRef = FirebaseDatabase.getInstance().getReference()
                .child("Groups").child("yeye").child(messageId);
        resltRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String winName = dataSnapshot.getValue(Message.class).getFromName();
                pesrtaid = dataSnapshot.getValue(Message.class).getFromId();
                String winNilai = dataSnapshot.getValue(Message.class).getMessage();
                String gckey = dataSnapshot.getValue(Message.class).getGcId();
                resltUsername.setText(winName);
                resltNilaiakhir.setText(winNilai);
                lelbrjalankey.setText(gckey);
                reslidpeserta.setText(pesrtaid);

                getlelbrjlnbygc();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getlelbrjlnbygc(){
        String lbkey = lelbrjalankey.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelbrjalanbygcResponse> call = service.getlelbrjalanbygc(
                lbkey, lbuserid, lbhewanid, lbharga, lbcomment);

        call.enqueue(new Callback<GetlelbrjalanbygcResponse>() {
            @Override
            public void onResponse(Call<GetlelbrjalanbygcResponse> call, Response<GetlelbrjalanbygcResponse> response) {
                if (response.isSuccessful()){
                    resltHargaawal.setText(response.body().getSuccess().getHarga());
                    reslidlelbrjalan.setText(response.body().getSuccess().getId());
                    resltPelelangid.setText(response.body().getSuccess().getUserId());
                    reslhewanid.setText(response.body().getSuccess().getHewanId());
                }
            }

            @Override
            public void onFailure(Call<GetlelbrjalanbygcResponse> call, Throwable t) {

            }
        });
    }

    private void addhasillelang(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddHasillelangResponse> call = service.hasilLelang(
                stathlelid.getText().toString().trim(),
                statshlel.getText().toString().trim(),
                reslidpeserta.getText().toString().trim(),
                resltPelelangid.getText().toString().trim(),
                reslidlelbrjalan.getText().toString().trim(),
                reslhewanid.getText().toString().trim(),
                resltHargaawal.getText().toString().trim(),
                resltNilaiakhir.getText().toString().trim()
        );

        call.enqueue(new Callback<AddHasillelangResponse>() {
            @Override
            public void onResponse(Call<AddHasillelangResponse> call, Response<AddHasillelangResponse> response) {
                if (response.isSuccessful()){
                    tohome();
                }
            }

            @Override
            public void onFailure(Call<AddHasillelangResponse> call, Throwable t) {

            }
        });

    }
    private void tohome(){
        Intent intent = new Intent(LelangResultActivity.this, HomeActivity.class);
        startActivity(intent);
    }


}