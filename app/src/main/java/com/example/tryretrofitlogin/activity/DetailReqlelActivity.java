package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.postresponse.addlelpesertamanager.AddlelpsrtmanagerResponse;
import com.example.tryretrofitlogin.responses.deletereqlel.DeletereqlelResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyid.GetreqlelbyidResponse;
import com.example.tryretrofitlogin.responses.getusernamebyid.GetusernamebyidResponse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailReqlelActivity extends AppCompatActivity {

    private String reqlelid,userid,namauser,namapendaftar;
    private String lelcomment, lelhewan, lelpelelang, lelharga,lelimgtokn;
    private TextView tmpReqlelid, tmpReqiduser, tmpReqidpendaftar,tmpReqlelidlelang,tmpHewanid,tmpTokenImgHw;
    private TextView txtLelang, txtUser, txtPendaftar, txtHewan, txtHargaLel;
    private Button btnReqaccept, btnReqreject, btnCekhewan;
    private ImageView btnBack;
    private int hargaDetreq;
    private DatabaseReference pesertamanagerRef,pesertakeyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reqlel);

        txtLelang = (TextView) findViewById(R.id.txt_reqlelid);
        txtUser = (TextView) findViewById(R.id.txt_reqleluser);
        txtPendaftar = (TextView) findViewById(R.id.txt_reqlelpengirim);
        txtHewan = (TextView)findViewById(R.id.reqlel_txt3);
        txtHargaLel = (TextView) findViewById(R.id.reqlel_hargalelang);
        tmpReqlelid = (TextView) findViewById(R.id.tmp_reqlelid);
        tmpTokenImgHw = (TextView) findViewById(R.id.tmptokenfotohw);
        tmpReqiduser = (TextView) findViewById(R.id.tmp_reqlel_iduser);
        tmpReqidpendaftar = (TextView) findViewById(R.id.tmp_reqlel_idpendaftar);
        tmpReqlelidlelang = (TextView) findViewById(R.id.tmp_reqlel_idlelang);
        tmpHewanid = (TextView) findViewById(R.id.tmphewanidreqlel);
        btnReqaccept = (Button) findViewById(R.id.btn_reqlelaccept);
        btnReqreject = (Button) findViewById(R.id.btn_reqlelreject);
        btnCekhewan = (Button) findViewById(R.id.reqlel_tofotohewan);
        btnBack = (ImageView)findViewById(R.id.btn_backdetreqlist);

        Intent reqintent = getIntent();
        reqlelid = reqintent.getStringExtra("reqlelid");
        tmpReqlelid.setText(reqlelid);
        Toast.makeText(this, "reqid" + reqlelid, Toast.LENGTH_SHORT).show();

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpReqiduser.setText(userid);
        namauser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

        pesertamanagerRef = FirebaseDatabase.getInstance().getReference().child("PesertaOnlineManager").child("qwerty");
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtUser.setText(namauser);
        getReqlel();

        btnReqaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                acceptReq();
                addstatuspesertaonlineFirebase();
            }
        });

        btnReqreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getReqlel(){
        String reqlelkey = tmpReqlelid.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetreqlelbyidResponse> call = service.getReqlelbyid(reqlelkey);

        call.enqueue(new Callback<GetreqlelbyidResponse>() {
            @Override
            public void onResponse(Call<GetreqlelbyidResponse> call, Response<GetreqlelbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpReqlelidlelang.setText(response.body().getSuccess().getLelbrjalanId());
                    tmpReqidpendaftar.setText(response.body().getSuccess().getPengirimId());
                    getNamauser();
                    getdetaillelang();
                }
            }

            @Override
            public void onFailure(Call<GetreqlelbyidResponse> call, Throwable t) {

            }
        });

    }

    private void getNamauser(){
        String pendaftarkey = tmpReqidpendaftar.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetusernamebyidResponse> call = service.getUsername(
                pendaftarkey,namapendaftar);

        call.enqueue(new Callback<GetusernamebyidResponse>() {
            @Override
            public void onResponse(Call<GetusernamebyidResponse> call, Response<GetusernamebyidResponse> response) {
                if (response.isSuccessful()){
                    txtPendaftar.setText(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<GetusernamebyidResponse> call, Throwable t) {

            }
        });
    }

    private void getdetaillelang(){
        String lkey = tmpReqlelidlelang.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelangbyidResponse> call = service.getdetlelang(
                lkey,lelcomment, lelhewan, lelpelelang, lelharga,lelimgtokn);

        call.enqueue(new Callback<GetlelangbyidResponse>() {
            @Override
            public void onResponse(Call<GetlelangbyidResponse> call, Response<GetlelangbyidResponse> response) {
                if (response.isSuccessful()){
                    txtLelang.setText(response.body().getSuccess().getComment());
                    tmpHewanid.setText(response.body().getSuccess().getHewanId());

                    hargaDetreq = response.body().getSuccess().getHarga();
                    //ubahformat Rp.
                    Locale localID = new Locale("in","ID");

                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                    txtHargaLel.setText(formatRupiah.format((double)hargaDetreq));

                    tmpTokenImgHw.setText(response.body().getSuccess().getImg_lelang());
                    getNamaHewan();
                    btnCekhewan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String reqlelfoto = tmpTokenImgHw.getText().toString().trim();
                            Intent intent = new Intent(DetailReqlelActivity.this, FotohewanRetrieve.class);
                            intent.putExtra("imgfotohw", reqlelfoto );
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetlelangbyidResponse> call, Throwable t) {

            }
        });
    }

    private void acceptReq(){
        String lelkey = tmpReqlelidlelang.getText().toString().trim();
        String pesertakey = tmpReqidpendaftar.getText().toString().trim();
        String hewan = txtHewan.getText().toString().trim();
        Toast.makeText(this, "test" + lelkey + pesertakey + hewan, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddlelpsrtmanagerResponse> call = service.accPeserta(
                pesertakey,lelkey,hewan
        );

        call.enqueue(new Callback<AddlelpsrtmanagerResponse>() {
            @Override
            public void onResponse(Call<AddlelpsrtmanagerResponse> call, Response<AddlelpsrtmanagerResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailReqlelActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    deleteReq();
                    toMain();
                }
            }

            @Override
            public void onFailure(Call<AddlelpsrtmanagerResponse> call, Throwable t) {
                Toast.makeText(DetailReqlelActivity.this, "sukses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addstatuspesertaonlineFirebase(){
        String idpeserta = tmpReqidpendaftar.getText().toString().trim();
        pesertakeyRef = pesertamanagerRef.child("peserta"+idpeserta);

        HashMap<String, Object> pesertastatusinfo = new HashMap<>();
        pesertastatusinfo.put("peserta_id",idpeserta);
        pesertastatusinfo.put("peserta_token","qwerty");
        pesertastatusinfo.put("status","offline");
        pesertakeyRef.updateChildren(pesertastatusinfo);
    }

    private void deleteReq(){
        String reqkey = tmpReqlelid.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call <DeletereqlelResponse> call = service.deleteReqlel(reqkey);

        call.enqueue(new Callback<DeletereqlelResponse>() {
            @Override
            public void onResponse(Call<DeletereqlelResponse> call, Response<DeletereqlelResponse> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<DeletereqlelResponse> call, Throwable t) {

            }
        });
    }

    private void toMain(){
        Intent intent = new Intent(DetailReqlelActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void getNamaHewan(){
        String hkey = tmpHewanid.getText().toString().trim();
        Log.d("hkey", hkey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GethewanbyidResponse> call = service.gethewanjenis(
                hkey, lelhewan
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
