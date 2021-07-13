package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.putresponse.putgchatid.UpdategchatResponse;
import com.example.tryretrofitlogin.responses.detlelbrjalanbyid.DetlelbrjalanbyidResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyid.GetlelbrjalanbyidResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLelbrjalan extends AppCompatActivity {
    private String lelbrjalanid,userid,lelbrjalanhewan,lelbrjalangctoken,groupname,generategcid,bukaharga,username;
    private String hargaKeyref;
    private TextView edTxtlelbrjlhewan,edTxtnilaiawal,detticektnamapeserta;
    private TextView tmpgctoken,tmpidlelbrjalan,tmplelbrjlniduser,tmplelbrjlnidhewan,tmppelelang,tmpgcgenerated;
    private Button btntogchat,btnmulailel;
    private DatabaseReference rootRef,groupRef,lelhargaref,lelhargaKeyref;
    private int hargaawaldetlelber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelbrjalan);

        tmppelelang = (TextView)findViewById(R.id.tmppelelang);
        tmpgctoken = (TextView)findViewById(R.id.gctoken);
        tmpgcgenerated = (TextView)findViewById(R.id.tmpgcidgenerated);
        tmpidlelbrjalan = (TextView)findViewById(R.id.tmp_idlelbrjalan);
        tmplelbrjlniduser = (TextView)findViewById(R.id.tmplelbrjln_userId);
        tmplelbrjlnidhewan = (TextView)findViewById(R.id.tmplelbrjln_idhewan);
        edTxtnilaiawal = (TextView)findViewById(R.id.edtxtlbrjln_nilaiawal);
        edTxtlelbrjlhewan = (TextView) findViewById(R.id.edtxtlelbrjln_hewan);
        detticektnamapeserta = (TextView) findViewById(R.id.detticekt_namapeserta);
        btntogchat = (Button)findViewById(R.id.btnto_gchat);
        btnmulailel = (Button)findViewById(R.id.btn_mulailelang);

        edTxtnilaiawal.setEnabled(false);
        edTxtlelbrjlhewan.setEnabled(false);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Groups");
        lelhargaref = FirebaseDatabase.getInstance().getReference().child("LelHargaMng");

        Intent lelintent = getIntent();
        lelbrjalanid = lelintent.getStringExtra("idlelbrjalan");
        tmpidlelbrjalan.setText(lelbrjalanid);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        tmplelbrjlniduser.setText(userid);
        detticektnamapeserta.setText(username);

        getLelbrjalan();

        btnmulailel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupname = rootRef.push().getKey();
                mulailelang();
                mulailelangFirebase(groupname);
                Intent intent = new Intent(DetailLelbrjalan.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        btntogchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tmpgctoken.getText().toString().trim().equals("Belum Mulai")){
                    Toast.makeText(DetailLelbrjalan.this, "Belum Mulai", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(DetailLelbrjalan.this, "gc" + tmpgctoken, Toast.LENGTH_SHORT).show();
                    Intent lelaspelelang = new Intent(DetailLelbrjalan.this,GroupchatActivity.class);
                    lelaspelelang.putExtra("gchattoken",tmpgctoken.getText());
                    DetailLelbrjalan.this.startActivity(lelaspelelang);
                }
            }
        });
    }

    private void getLelbrjalan(){
        String lelbrjlnkey =tmpidlelbrjalan.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<DetlelbrjalanbyidResponse> call = service.detletbrjalanbyid(
                lelbrjlnkey, lelbrjalangctoken);

        call.enqueue(new Callback<DetlelbrjalanbyidResponse>() {
            @Override
            public void onResponse(Call<DetlelbrjalanbyidResponse> call, Response<DetlelbrjalanbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpgctoken.setText(response.body().getSuccess().getGchatId());

                    hargaawaldetlelber = response.body().getSuccess().getHarga();

                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    edTxtnilaiawal.setText(formatRupiah.format((double)hargaawaldetlelber));

                    tmppelelang.setText(response.body().getSuccess().getUserId());
                    tmplelbrjlnidhewan.setText(response.body().getSuccess().getHewanId());
                    getNamaHewan();
                    if (tmpgctoken.getText().toString().trim().equals("Belum Mulai") &&
                            (tmppelelang.getText().toString().trim().equals(userid))){
                        btnmulailel.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DetlelbrjalanbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getNamaHewan(){
        String hkey = tmplelbrjlnidhewan.getText().toString().trim();
        Log.d("hkey", hkey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GethewanbyidResponse> call = service.gethewanjenis(
                hkey, lelbrjalanhewan
        );

        call.enqueue(new Callback<GethewanbyidResponse>() {
            @Override
            public void onResponse(Call<GethewanbyidResponse> call, Response<GethewanbyidResponse> response) {
                if (response.isSuccessful()){
                    edTxtlelbrjlhewan.setText(response.body().getSuccess().getJenis());
                }
            }

            @Override
            public void onFailure(Call<GethewanbyidResponse> call, Throwable t) {

            }
        });
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private static String generategcid(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

    private void mulailelang(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<UpdategchatResponse> call = service.updatemulai(
                tmpidlelbrjalan.getText().toString().trim(),
                groupname);

        call.enqueue(new Callback<UpdategchatResponse>() {
            @Override
            public void onResponse(Call<UpdategchatResponse> call, Response<UpdategchatResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DetailLelbrjalan.this, "Lelang Dibuka", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UpdategchatResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    } //fungsi mulainya taruh di detaaillelberjaan//

    private void mulailelangFirebase(final String groupname){
        String bidharga = edTxtnilaiawal.getText().toString().trim();
        DatabaseReference ref = rootRef.child(groupname);
        ref.setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        lelhargaKeyref = lelhargaref.child(groupname);
        HashMap<String, Object> msgInfomap = new HashMap<>();
        msgInfomap.put("harga",bidharga);
        lelhargaKeyref.updateChildren(msgInfomap);

        //untukisivaluemsg
    }
}