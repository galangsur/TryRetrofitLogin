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
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Message;
import com.example.tryretrofitlogin.postresponse.addhasillelang.AddHasilLelangResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbygc.GetlelbrjalanbygcResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LelangResultActivity extends AppCompatActivity {
    private TextView resltMsgid, resltUserid,  resltPelelangid, resltPelelangname, lelbrjalankey;
    private TextView reslidlelbrjalan, reslidpeserta, statshlel,stathlelid, reslhewanid,reslimgparent,reslhargaawaltmp,reslhargaakhirtmp;
    private EditText resltUsername, resltNilaiakhir, resltHargaawal,resltNamahewan,resltUmurhewan, resltJenkelaminhewan, resltDeskripsihewan;
    private Button resltBtntohome, resltbtnNota;
    private String messageId,lbid,lbuserid,lbhewanid,lbharga,groupid,userid,
            lbcomment,statushasilawal,statushasilawalid,pesrtaid,lelresimgparent;
    private int hargaawalresult, hargaakhirresult, lbreqwaktu, lbreqnominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang_result);

        reslimgparent = (TextView) findViewById(R.id.reslelimgprnt);
        resltMsgid = (TextView) findViewById(R.id.reslt_messageId);
        resltUserid = (TextView) findViewById(R.id.reslt_userId);
        lelbrjalankey = (TextView) findViewById(R.id.lelbrjalankey);
        resltUsername = (EditText) findViewById(R.id.rslt_namapemenang);
        resltNilaiakhir = (EditText) findViewById(R.id.rslt_nilaiakhir);
        resltHargaawal = (EditText) findViewById(R.id.rslt_hargaawal);
        resltNamahewan = (EditText) findViewById(R.id.rslt_namahewan);
        resltUmurhewan = (EditText) findViewById(R.id.rslt_umurhewan);
        resltJenkelaminhewan = (EditText) findViewById(R.id.rslt_jeniskelaminhewan);
        resltDeskripsihewan = (EditText) findViewById(R.id.rslt_deskripsiwarnahewan);
        resltBtntohome = (Button) findViewById(R.id.reslt_tohome);
        resltbtnNota = (Button) findViewById(R.id.reslt_buattrans);
        resltPelelangid = (TextView) findViewById(R.id.rsltidpelelang);
        reslidlelbrjalan = (TextView) findViewById(R.id.rsltidlelbrjalan);
        reslidpeserta = (TextView) findViewById(R.id.reslt_pesertaId);
        statshlel = (TextView)findViewById(R.id.txtstatshasilawal);
        stathlelid = (TextView)findViewById(R.id.txtstatshasilawalid);
        reslhewanid = (TextView)findViewById(R.id.reslt_hewanid);
        reslhargaawaltmp = (TextView)findViewById(R.id.rslthargaawaltmp);
        reslhargaakhirtmp = (TextView)findViewById(R.id.rslthargaakhirtmp);

        resltUsername.setKeyListener(null);
        resltNilaiakhir.setKeyListener(null);
        resltHargaawal.setKeyListener(null);
        resltNamahewan.setKeyListener(null);
        resltUmurhewan.setKeyListener(null);
        resltJenkelaminhewan.setKeyListener(null);
        resltDeskripsihewan.setKeyListener(null);
        stathlelid.setText("1");
        statshlel.setText("Belum Dibayarkan");
        reslimgparent.setText(lelresimgparent());

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();

        Intent gcIntent = getIntent();
        groupid = gcIntent.getStringExtra("groupId");
        messageId = gcIntent.getStringExtra("messageId");

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getresltdetail();

        resltbtnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addhasillelang();
                Toast.makeText(LelangResultActivity.this, "Nota Transaksi Dibuat", Toast.LENGTH_SHORT).show();
            }
        });

        resltBtntohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tohome();
            }
        });
    }

    private void getresltdetail(){
        DatabaseReference resltRef = FirebaseDatabase.getInstance().getReference()
                .child("Groups").child(groupid).child(messageId);
        resltRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String winName = dataSnapshot.getValue(Message.class).getFromName();
                pesrtaid = dataSnapshot.getValue(Message.class).getFromId();

                String hargaakhirresultstring = dataSnapshot.getValue(Message.class).getMessage();
                hargaakhirresult = Integer.parseInt(hargaakhirresultstring);
                reslhargaakhirtmp.setText(hargaakhirresultstring);

                String gckey = dataSnapshot.getValue(Message.class).getGcId();

                resltUsername.setText(winName);

                Locale localID = new Locale("in","ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                resltNilaiakhir.setText(formatRupiah.format((double)hargaakhirresult));

                lelbrjalankey.setText(gckey);
                reslidpeserta.setText(pesrtaid);

                if (userid.equals(pesrtaid)) {
                    resltBtntohome.setVisibility(View.INVISIBLE);
                }else{
                    resltbtnNota.setVisibility(View.INVISIBLE);
                }

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
                lbkey, lbuserid, lbhewanid, lbharga, lbcomment, lbreqwaktu, lbreqnominal);

        call.enqueue(new Callback<GetlelbrjalanbygcResponse>() {
            @Override
            public void onResponse(Call<GetlelbrjalanbygcResponse> call, Response<GetlelbrjalanbygcResponse> response) {
                if (response.isSuccessful()){
                    hargaawalresult = response.body().getSuccess().getHarga();
                    String hargaawalresultstring = String.valueOf(hargaawalresult);
                    reslhargaawaltmp.setText(hargaawalresultstring);

                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    resltHargaawal.setText(formatRupiah.format((double)hargaawalresult));

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

    private void  addhasillelang(){
        String paramA = reslimgparent.getText().toString().trim();
//        Toast.makeText(this, "parama"+paramA, Toast.LENGTH_SHORT).show();
        String paramB = "1";
//        Toast.makeText(this, "paramb"+paramB, Toast.LENGTH_SHORT).show();
        String paramC = "Belum Dibayarkan";
//        Toast.makeText(this, "paramc"+paramC, Toast.LENGTH_SHORT).show();
        String paramD = reslidpeserta.getText().toString().trim();
//        Toast.makeText(this, "paramd"+paramD, Toast.LENGTH_SHORT).show();
        String paramE = resltPelelangid.getText().toString().trim();
//        Toast.makeText(this, "parame"+paramE, Toast.LENGTH_SHORT).show();
        String paramF = reslidlelbrjalan.getText().toString().trim();
//        Toast.makeText(this, "paramf"+paramF, Toast.LENGTH_SHORT).show();
        String paramG = reslhewanid.getText().toString().trim();
//        Toast.makeText(this, "paramg"+paramG, Toast.LENGTH_SHORT).show();
        String paramH = reslhargaawaltmp.getText().toString().trim();
//        Toast.makeText(this, "paramh"+paramH, Toast.LENGTH_SHORT).show();
        String paramI = reslhargaakhirtmp.getText().toString().trim();
//        Toast.makeText(this, "parami"+paramI, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddHasilLelangResponse> call = service.addhasilLelang(
                paramA,paramB,paramC,paramD,paramE,paramF,paramG,paramH,paramI);

        call.enqueue(new Callback<AddHasilLelangResponse>() {
            @Override
            public void onResponse(Call<AddHasilLelangResponse> call, Response<AddHasilLelangResponse> response) {
                if(response.isSuccessful()){
                    tohome();
                }
            }

            @Override
            public void onFailure(Call<AddHasilLelangResponse> call, Throwable t) {
                Toast.makeText(LelangResultActivity.this, "proses failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tohome(){
        Intent intent = new Intent(LelangResultActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private static String lelresimgparent(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

    //    private void addhasillelang(){
//        Toast.makeText(this, "cingz", Toast.LENGTH_SHORT).show();
//        String paramA = reslimgparent.getText().toString().trim();
//        String paramB = stathlelid.getText().toString().trim();
//        String paramC = statshlel.getText().toString().trim();
//        String paramD = reslidpeserta.getText().toString().trim();
//        String paramE = resltPelelangid.getText().toString().trim();
//        String paramF = reslhewanid.getText().toString().trim();
//        String paramG = resltHargaawal.getText().toString().trim();
//        String paramH = resltNilaiakhir.getText().toString().trim();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(APIUrl.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService service = retrofit.create(APIService.class);
//
//        Call<LeltransAddResponse> call = service.addleltrans(
//                paramA,paramB,paramC,paramD,paramE,paramF,paramG,paramH
//        );
//
//        call.enqueue(new Callback<LeltransAddResponse>() {
//            @Override
//            public void onResponse(Call<LeltransAddResponse> call, Response<LeltransAddResponse> response) {
//                if (response.isSuccessful()){
//                    Toast.makeText(LelangResultActivity.this, "suksesyan", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LeltransAddResponse> call, Throwable t) {
//            }
//        });
//
//    }
}