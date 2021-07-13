package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.putresponse.putgchatid.UpdategchatResponse;
import com.example.tryretrofitlogin.responses.detlelbrjalanbyid.DetlelbrjalanbyidResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLelbrjalanPlelang extends AppCompatActivity {

    private AlertDialog.Builder dialogblistpeserta;
    private AlertDialog dialog;
    private String lelbrjalanid,userid,lelbrjalanhewan,lelbrjalangctoken,groupname,generategcid,bukaharga,username;
    private String hargaKeyref;
    private TextView edTxtlelbrjlhewan,edTxtnilaiawal,detticektnamapeserta,tmpdialogpeserta,textdialog;
    private TextView tmpgctoken,tmpidlelbrjalan,tmplelbrjlniduser,tmplelbrjlnidhewan,tmppelelang,tmpgcgenerated;
    private DatabaseReference rootRef,groupRef,lelhargaref,lelhargaKeyref;
    private Button btntogchat,btnmulailel,btnlistpesertadialog;
    private int hargaawaldetlelberpelelang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelbrjalan_plelang);

        tmpidlelbrjalan = (TextView)findViewById(R.id.tmpmulailel_lelid);
        tmplelbrjlnidhewan = (TextView)findViewById(R.id.tmpmulailel_hewanid);
        tmpgctoken = (TextView)findViewById(R.id.tmpmulailel_gchattoken);
        tmppelelang = (TextView)findViewById(R.id.tmpmulailel_pelelangid);
        edTxtlelbrjlhewan = (TextView)findViewById(R.id.txtmulailel_namahewan);
        edTxtnilaiawal = (TextView)findViewById(R.id.txtmulailel_harga);
        btnmulailel = (Button)findViewById(R.id.btn_MulaiLelang);
        btnlistpesertadialog = (Button)findViewById(R.id.listpesertadialog);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Groups");
        lelhargaref = FirebaseDatabase.getInstance().getReference().child("LelHargaMng");

        Intent lelintent = getIntent();
        lelbrjalanid = lelintent.getStringExtra("idlelbrjalan");
        tmpidlelbrjalan.setText(lelbrjalanid);

        getLelbrjalan();

        btnmulailel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupname = rootRef.push().getKey();
                mulailelang();
                mulailelangFirebase(groupname);
                Intent intent = new Intent(DetailLelbrjalanPlelang.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnlistpesertadialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglistpeserta();
            }
        });
    }

    private void dialoglistpeserta(){
        dialogblistpeserta = new AlertDialog.Builder(DetailLelbrjalanPlelang.this);
        final View listpesertaView = getLayoutInflater().inflate(R.layout.dialog_listpeserta,null);

        dialogblistpeserta.setView(listpesertaView);
        dialog = dialogblistpeserta.create();
        dialog.show();

        getNamaHewandialog();
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

                    hargaawaldetlelberpelelang = response.body().getSuccess().getHarga();

                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    edTxtnilaiawal.setText(formatRupiah.format((double)hargaawaldetlelberpelelang));

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

    private void getNamaHewandialog(){
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
                    textdialog.setText(response.body().getSuccess().getJenis());
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
                    Toast.makeText(DetailLelbrjalanPlelang.this, "Lelang Dibuka", Toast.LENGTH_SHORT).show();

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