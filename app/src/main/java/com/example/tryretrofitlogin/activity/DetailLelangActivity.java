package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.HorizImageRVadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Reqlelang;
import com.example.tryretrofitlogin.postresponse.addrequestlelang.AddreqlelangResponse;
import com.example.tryretrofitlogin.putresponse.putgchatid.UpdategchatResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getimgbyparent.GetimgbyparentResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getuserbyid.GetusernamebyidResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLelangActivity extends AppCompatActivity {

    private AlertDialog.Builder dialoglihatfoto;
    private AlertDialog dialogfoto;
    private RecyclerView imageRvfotohewan;
    private String lelangid, inviscond, groupname;
    private String lelcomment, lelhewan, lelpelelang, lelharga, namahewan, namauser, userid, lelimgparent;
    private TextView txtComment, txtHewan, txtPemilik, txtHarga, tmpfotoHewankey, tmpfotoSertifkey;
    private TextView tmpidlelang, tmpidhewan, tmpidpelelang, tmpiduser,tmpidlelbrjalan,tmpimgtokenhw;
    private Button btnDaftar, btnMulailel,btnLihatHewan,btnLihatSertif;
    private DatabaseReference rootRef;
    private int hargaawaldetlel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang);

        txtComment = (TextView) findViewById(R.id.txt_leldetcomment);
        txtHewan = (TextView) findViewById(R.id.txt_leldethewan);
        txtPemilik = (TextView) findViewById(R.id.txt_leldetnama);
        txtHarga = (TextView) findViewById(R.id.txt_leldetharga);
        tmpidlelang = (TextView) findViewById(R.id.tmp_idlelang);
        tmpidhewan = (TextView) findViewById(R.id.tmp_idhewan);
        tmpidpelelang = (TextView) findViewById(R.id.tmp_idpelelang);
        tmpiduser = (TextView) findViewById(R.id.tmp_iduser);
        tmpidlelbrjalan = (TextView) findViewById(R.id.tmp_idlelbrjalan);
        tmpimgtokenhw = (TextView) findViewById(R.id.tmpimghwtoken);
        btnDaftar = (Button) findViewById(R.id.btn_daftarlel);
        btnLihatHewan = (Button) findViewById(R.id.btn_detlelfotohewan);
        btnLihatSertif = (Button) findViewById(R.id.btn_detlelfotosertif);
        btnDaftar.setVisibility(View.VISIBLE);

        rootRef = FirebaseDatabase.getInstance().getReference();

        Intent lelintent = getIntent();
        lelangid = lelintent.getStringExtra("lelid");
        tmpidlelang.setText(lelangid);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpiduser.setText(userid);



    }

    @Override
    protected void onStart() {
        super.onStart();
        getLelang();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarLelang();
            }
        });

        btnLihatHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogfotohewan();

//                String fotohewan = tmpimgtokenhw.getText().toString().trim();
//                Intent intent = new Intent(DetailLelangActivity.this, FotohewanRetrieve.class);
//                intent.putExtra("imgfotohw", fotohewan );
//                startActivity(intent);
            }
        });

        btnLihatSertif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogfotosertif();
            }
        });



    }

    private void dialogfotohewan(){
        dialoglihatfoto = new AlertDialog.Builder(DetailLelangActivity.this);
        final View listfotohewanView = getLayoutInflater().inflate(R.layout.activity_fotohewan_retrieve,null);

        tmpfotoHewankey = (TextView) listfotohewanView.findViewById(R.id.tmpkeyimghewan);
        imageRvfotohewan = (RecyclerView) listfotohewanView.findViewById(R.id.RV_hewanfoto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,false);
        imageRvfotohewan.setLayoutManager(layoutManager);
        imageRvfotohewan.setHasFixedSize(true);

        dialoglihatfoto.setView(listfotohewanView);
        dialogfoto = dialoglihatfoto.create();
        dialogfoto.show();

        getAllImage();
    }
    private void dialogfotosertif(){
        dialoglihatfoto = new AlertDialog.Builder(DetailLelangActivity.this);
        final View listfotosertifView = getLayoutInflater().inflate(R.layout.dialog_listpeserta,null);

        tmpfotoSertifkey = (TextView) listfotosertifView.findViewById(R.id.tmpkeyimgsertif);
        imageRvfotohewan = (RecyclerView) listfotosertifView.findViewById(R.id.RV_sertiffoto);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,false);
        imageRvfotohewan.setLayoutManager(layoutManager);
        imageRvfotohewan.setHasFixedSize(true);

        dialoglihatfoto.setView(listfotosertifView);
        dialogfoto = dialoglihatfoto.create();
        dialogfoto.show();

        getSertifImage();
    }

    public void getAllImage(){
        String key = tmpfotoHewankey.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetimgbyparentResponse> call = service.getimgbyparent(
                key
        );

        call.enqueue(new Callback<GetimgbyparentResponse>() {
            @Override
            public void onResponse(Call<GetimgbyparentResponse> call, Response<GetimgbyparentResponse> response) {
                if (response.isSuccessful()){
                    HorizImageRVadapt horizImageRVadapt =  new HorizImageRVadapt(DetailLelangActivity.this,response.body()
                            .getSuccess());
                    horizImageRVadapt.notifyDataSetChanged();
                    imageRvfotohewan.setAdapter(horizImageRVadapt);
                }
            }

            @Override
            public void onFailure(Call<GetimgbyparentResponse> call, Throwable t) {
                Toast.makeText(DetailLelangActivity.this, "gkkeget", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSertifImage(){
        String key = tmpfotoSertifkey.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetimgbyparentResponse> call = service.getimgbyparent(
                key
        );

        call.enqueue(new Callback<GetimgbyparentResponse>() {
            @Override
            public void onResponse(Call<GetimgbyparentResponse> call, Response<GetimgbyparentResponse> response) {
                if (response.isSuccessful()){
                    HorizImageRVadapt horizImageRVadapt =  new HorizImageRVadapt(DetailLelangActivity.this,response.body()
                            .getSuccess());
                    horizImageRVadapt.notifyDataSetChanged();
                    imageRvfotohewan.setAdapter(horizImageRVadapt);
                }
            }

            @Override
            public void onFailure(Call<GetimgbyparentResponse> call, Throwable t) {
                Toast.makeText(DetailLelangActivity.this, "gkkeget", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getLelang(){
        String lkey = tmpidlelang.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyidResponse> call = service.getdetlelang(
                lkey, lelcomment, lelhewan, lelpelelang, lelharga,lelimgparent);

        call.enqueue(new Callback<GetlelangbyidResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyidResponse> call, Response<GetlelangbyidResponse> response) {
                if (response.isSuccessful()){
                    txtComment.setText(response.body().getSuccess().getComment());
                    tmpidhewan.setText(response.body().getSuccess().getHewanId());
                    tmpidpelelang.setText(response.body().getSuccess().getUserId());

                    hargaawaldetlel = response.body().getSuccess().getHarga();

                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    txtHarga.setText(formatRupiah.format((double)hargaawaldetlel));


                    tmpimgtokenhw.setText(response.body().getSuccess().getImg_lelang());
                    getNamaHewan();
                    getNamaUser();

                    inviscond = tmpidpelelang.getText().toString().trim();
                    if (userid.equals(inviscond)){
                        Toast.makeText(DetailLelangActivity.this, "pemilik lelang", Toast.LENGTH_SHORT).show();
                        btnDaftar.setVisibility(View.INVISIBLE);
//                        btnMulailel.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(DetailLelangActivity.this, "pendaftar", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetlelangbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getNamaHewan(){
        String hkey = tmpidhewan.getText().toString().trim();
        Log.d("hkey", hkey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GethewanbyidResponse> call = service.gethewanjenis(
                hkey, namahewan
        );

        call.enqueue(new Callback<GethewanbyidResponse>() {
            @Override
            public void onResponse(Call<GethewanbyidResponse> call, Response<GethewanbyidResponse> response) {
                if (response.isSuccessful()){
                    txtHewan.setText(response.body().getSuccess().getJenis());
                }
            }

            @Override
            public void onFailure(Call<GethewanbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getNamaUser(){
        String ukey = tmpidpelelang.getText().toString().trim();
        Log.d("ukey", ukey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetusernamebyidResponse> call = service.getUsername(
                ukey, namauser
        );

        call.enqueue(new Callback<GetusernamebyidResponse>() {
            @Override
            public void onResponse(Call<GetusernamebyidResponse> call, Response<GetusernamebyidResponse> response) {
                if (response.isSuccessful()){
                    txtPemilik.setText(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<GetusernamebyidResponse> call, Throwable t) {

            }
        });
    }

    private void daftarLelang(){
        String pky = tmpidpelelang.getText().toString().trim();
        Log.d("pky", pky);
        String lky = tmpidlelang.getText().toString().trim();
        Log.d("lky", lky);
        String uky = tmpiduser.getText().toString().trim();
        Log.d("uky", uky);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Reqlelang reqlelang = new Reqlelang(uky,pky,lky);

        Call<AddreqlelangResponse> call = service.reqLelang(
                uky, pky, lky
        );

        call.enqueue(new Callback<AddreqlelangResponse>() {
            @Override
            public void onResponse(Call<AddreqlelangResponse> call, Response<AddreqlelangResponse> response) {
                if (response.isSuccessful()){
                    tohome();
                }
            }

            @Override
            public void onFailure(Call<AddreqlelangResponse> call, Throwable t) {

            }
        });
    }

    private void mulailelang(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<UpdategchatResponse> call = service.updatemulai(
                tmpidlelang.getText().toString().trim(),
                groupname);

        call.enqueue(new Callback<UpdategchatResponse>() {
            @Override
            public void onResponse(Call<UpdategchatResponse> call, Response<UpdategchatResponse> response) {
                Toast.makeText(getApplicationContext(), response.body().getSuccess().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UpdategchatResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    } //fungsi mulainya taruh di detaaillelberjaan//

    private void mulailelangFirebase(String groupname){

            rootRef.child("Groups").child(groupname).setValue("")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                            }
                        }
                    });
    }

    private void tohome(){
        Intent intent = new Intent(DetailLelangActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
