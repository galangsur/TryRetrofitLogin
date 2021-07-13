package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.postresponse.addtranscomp.ResponseAddtranscomp;
import com.example.tryretrofitlogin.responses.getalasancomplaint.GetAlasanComplaintResponse;
import com.example.tryretrofitlogin.responses.getalasancomplaint.SuccessItem;
import com.example.tryretrofitlogin.responses.getleltransbyimageparent.Getleltransbyimgparent;
import com.example.tryretrofitlogin.responses.getsolusicomplaint.GetSolusiComplaintResponse;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionComplaintActivity extends AppCompatActivity {

    private TextView txtuserid,txtusernama,txtidtransaksi,txtimgtokengetintent,txtimagetokentranscompupload;
    private Spinner spinAlasanid, spinSolusiid;
    private Button btnAddimagetranscomp;
    private ImageView btnbacktranscomp;
    private int idAlasan, idSolusi;
    private String Alasanid, jenisAlasan, Solusiid, jenisSolusi, compimgparent, usercomplaintid, usercomplaintname;
    private String imgparenttoken, transaksiid;
    public List<SuccessItem> alasans;
    public List<com.example.tryretrofitlogin.responses.getsolusicomplaint.SuccessItem> solusis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_complaint);

        txtuserid = (TextView) findViewById(R.id.txtuserIdcomplaint);
        txtusernama = (TextView) findViewById(R.id.txtuserNamacomplaint);
        txtidtransaksi = (TextView) findViewById(R.id.txttranscompIdtransaksi);
        txtimgtokengetintent = (TextView) findViewById(R.id.transcompimgtokenget);
        txtimagetokentranscompupload = (TextView) findViewById(R.id.transcompimgtokenupload);
        spinAlasanid = (Spinner) findViewById(R.id.spinAlasancomplaint);
        spinSolusiid = (Spinner) findViewById(R.id.spinSolusicomplaint);
        btnAddimagetranscomp = (Button) findViewById(R.id.btn_transcomp_addimage);
        btnbacktranscomp = (ImageView) findViewById(R.id.btncomplaintback);

        usercomplaintid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        usercomplaintname = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

        Intent compimgparentIntent = getIntent();
        imgparenttoken = compimgparentIntent.getStringExtra("imgparenttoken");
        txtimgtokengetintent.setText(imgparenttoken);

        txtuserid.setText(usercomplaintid);
        txtusernama.setText(usercomplaintname);
        txtimagetokentranscompupload.setText(lelimgparent());
        compimgparent = txtimagetokentranscompupload.getText().toString().trim();

        initSpinnerAlasan();
        spinAlasanid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAlasan = parent.getItemAtPosition(position).toString();
                idAlasan = alasans.get(position).getId();
                jenisAlasan = alasans.get(position).getAlasan();
//                Toast.makeText(TransactionComplaintActivity.this, selectedAlasan + "di pilih dengan id" + idAlasan +"jenis " +jenisAlasan, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initSpinnerSolusi();
        spinSolusiid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSolusi = parent.getItemAtPosition(position).toString();
                idSolusi = solusis.get(position).getId();
                jenisSolusi = solusis.get(position).getSolusi();
//                Toast.makeText(TransactionComplaintActivity.this, selectedSolusi + "di pilih dengan id" + idSolusi +"jenis " +jenisSolusi, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnbacktranscomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getleltransaksiid();

        btnAddimagetranscomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadTranscomp();
            }
        });
    }

    private void uploadTranscomp(){
        String idtransaksicomp = txtidtransaksi.getText().toString().trim();
        String idusercomp = txtuserid.getText().toString().trim();
        String namausercomp = txtusernama.getText().toString().trim();
        String imgcomptoken = txtimagetokentranscompupload.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResponseAddtranscomp> call = service.uploadTranscomp(
                idtransaksicomp,idusercomp,namausercomp,idAlasan,idSolusi,imgcomptoken
        );

        call.enqueue(new Callback<ResponseAddtranscomp>() {
            @Override
            public void onResponse(Call<ResponseAddtranscomp> call, Response<ResponseAddtranscomp> response) {
                touploadimg();
            }

            @Override
            public void onFailure(Call<ResponseAddtranscomp> call, Throwable t) {

            }
        });
    }

    private void initSpinnerAlasan(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        final Call<GetAlasanComplaintResponse> call = service.getallalasan();

        call.enqueue(new Callback<GetAlasanComplaintResponse>() {
            @Override
            public void onResponse(Call<GetAlasanComplaintResponse> call, Response<GetAlasanComplaintResponse> response) {
                if (response.isSuccessful()) {
                    alasans = response.body().getSuccess();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < alasans.size(); i++){
                        listSpinner.add(alasans.get(i).getAlasan());
                    }

                    //mengoper data ke spinner dengan adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransactionComplaintActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinAlasanid.setAdapter(adapter);
                }else {
                    Toast.makeText(TransactionComplaintActivity.this, "gagal memilih hewan cuok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAlasanComplaintResponse> call, Throwable t) {
                Toast.makeText(TransactionComplaintActivity.this, "koneksi cuok , koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerSolusi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        final Call<GetSolusiComplaintResponse> call = service.getallsolusi();

        call.enqueue(new Callback<GetSolusiComplaintResponse>() {
            @Override
            public void onResponse(Call<GetSolusiComplaintResponse> call, Response<GetSolusiComplaintResponse> response) {
                if (response.isSuccessful()) {
                    solusis = response.body().getSuccess();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < solusis.size(); i++){
                        listSpinner.add(solusis.get(i).getSolusi());
                    }

                    //mengoper data ke spinner dengan adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransactionComplaintActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinSolusiid.setAdapter(adapter);
                }else {
                    Toast.makeText(TransactionComplaintActivity.this, "gagal memilih hewan cuok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSolusiComplaintResponse> call, Throwable t) {
                Toast.makeText(TransactionComplaintActivity.this, "koneksi cuok , koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private static String lelimgparent(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

    private void touploadimg(){
        Intent intent = new Intent(TransactionComplaintActivity.this, Uploadhewan.class);
        intent.putExtra("imghw", compimgparent);
        startActivity(intent);
    }

    private void getleltransaksiid(){
        String imgparentkey = txtimgtokengetintent.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Getleltransbyimgparent> call = service.leltransbyleltoken(imgparentkey,transaksiid);

        call.enqueue(new Callback<Getleltransbyimgparent>() {
            @Override
            public void onResponse(Call<Getleltransbyimgparent> call, Response<Getleltransbyimgparent> response) {
                if (response.isSuccessful()){
                    txtidtransaksi.setText(response.body().getSuccess().getId().trim());
                    Toast.makeText(TransactionComplaintActivity.this, "" +
                            txtidtransaksi.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Getleltransbyimgparent> call, Throwable t) {

            }
        });
    }
}