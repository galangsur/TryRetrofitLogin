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
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.putresponse.putleltransstat.UpdateleltransstatResponse;
import com.example.tryretrofitlogin.responses.getalldatauserbyid.GetalldatauserbyidResponse;
import com.example.tryretrofitlogin.responses.getleltransbyid.GetleltransbyidResponse;
import com.example.tryretrofitlogin.responses.topupwallet.TopupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailTransaksiLelang extends AppCompatActivity {

    private String idtranslel,userid,psrtusera,pllguserb;
    private TextView tmpiddettranslel,tmpoldstatid,tmpoldstat,tmpnewstatid,tmpnewstat;
    private TextView tmpidusernow,tmpidpsrt,tmpidpllg,tmpimgparenttoken;
    private EditText nilaiakhir,usrnamepllg,usrnamepemenang,statustransaksi;
    private Button submittrans,submitfinish,tobukti;
    private ImageView btnback,imgtopelelangprofile,imgtopemenangprofile,imgtocomplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransaksi_lelang);

        tmpiddettranslel = (TextView)findViewById(R.id.txtiddettranslel);
        tmpoldstatid = (TextView)findViewById(R.id.txtidstatold);
        tmpoldstat = (TextView)findViewById(R.id.txtstatold);
        tmpnewstatid = (TextView)findViewById(R.id.txtidstatnew);
        tmpnewstat = (TextView) findViewById(R.id.txtstatnew);
        tmpidpllg = (TextView)findViewById(R.id.reslt_pllgId);
        tmpidpsrt = (TextView)findViewById(R.id.reslt_psrtId);
        tmpidusernow = (TextView)findViewById(R.id.reslt_usernow);
        tmpimgparenttoken = (TextView)findViewById(R.id.reslt_imgparenttoken);
        nilaiakhir = (EditText) findViewById(R.id.rslt_nilaiakhir);
        usrnamepllg = (EditText) findViewById(R.id.rslt_namapelelang);
        usrnamepemenang = (EditText) findViewById(R.id.rslt_namapemenang);
        statustransaksi = (EditText) findViewById(R.id.rslt_statustransaksi);
        submittrans = (Button)findViewById(R.id.leltrans_submit);
        submitfinish = (Button)findViewById(R.id.leltrans_finishtrans);
        tobukti = (Button)findViewById(R.id.btntobukti);
        btnback = (ImageView) findViewById(R.id.btn_backdettranslel);
        imgtopemenangprofile = (ImageView) findViewById(R.id.icondettrans5);
        imgtopelelangprofile = (ImageView) findViewById(R.id.icondettrans6);
        imgtocomplaint = (ImageView) findViewById(R.id.icondettrans4);

        nilaiakhir.setKeyListener(null);
        usrnamepllg.setKeyListener(null);
        usrnamepemenang.setKeyListener(null);
        statustransaksi.setKeyListener(null);

        Intent lelintent = getIntent();
        idtranslel = lelintent.getStringExtra("idtranslel");
        tmpiddettranslel.setText(idtranslel);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        tmpidusernow.setText(userid);

        getleltransbyid();

        submittrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnupdatetrans();
            }
        });

        submitfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmbhsaldopelelang();
                btnupdatetrans();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tobukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgparentbuktilama = tmpimgparenttoken.getText().toString().trim();
                Intent intent = new Intent(DetailTransaksiLelang.this, ImageRetrieveTry.class);
                intent.putExtra("imgbuktilama", imgparentbuktilama );
                startActivity(intent);
            }
        });

        imgtopemenangprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idpemenang_detrans = tmpidpsrt.getText().toString().trim();
                Intent intenttoprofpemenang = new Intent(DetailTransaksiLelang.this, UserprofileActivity.class);
                intenttoprofpemenang.putExtra("useridfrom_dettrans", idpemenang_detrans );
                startActivity(intenttoprofpemenang);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });

        imgtopelelangprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idpelelang_detrans = tmpidpllg.getText().toString().trim();
                Intent intenttoprofpelelang = new Intent(DetailTransaksiLelang.this, UserprofileActivity.class);
                intenttoprofpelelang.putExtra("useridfrom_dettrans", idpelelang_detrans );
                startActivity(intenttoprofpelelang);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getleltransbyid(){
        String leltranskey = tmpiddettranslel.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetleltransbyidResponse> call = service.leltransbyid(
          leltranskey
        );

        call.enqueue(new Callback<GetleltransbyidResponse>() {
            @Override
            public void onResponse(Call<GetleltransbyidResponse> call, Response<GetleltransbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpoldstatid.setText(response.body().getSuccess().getStatushasilId());
                    tmpoldstat.setText(response.body().getSuccess().getStatushasil());
                    statustransaksi.setText(response.body().getSuccess().getStatushasil());
                    tmpidpsrt.setText(response.body().getSuccess().getPesertaId());
                    tmpidpllg.setText(response.body().getSuccess().getPelelangId());
                    nilaiakhir.setText(response.body().getSuccess().getNilaiAkhir());
                    tmpimgparenttoken.setText(response.body().getSuccess().getHasillelangToken());
                    setnewstatid();
                    setnewstat();
                    btnsetting();
                    getnamapemenang();
                    getnamapllg();
                }
            }

            @Override
            public void onFailure(Call<GetleltransbyidResponse> call, Throwable t) {

            }
        });
    }

    private void setnewstatid(){
        if (tmpoldstatid.getText().toString().equals("1")){
            tmpnewstatid.setText("2");
            submittrans.setText("Submit Bayar");//pelelang dan penjual tidak bisa
        }else if (tmpoldstatid.getText().toString().equals("2")){
            tmpnewstatid.setText("3");
            submittrans.setText("Submit Pengiriman");//pembeli dan peserta tidak bisa
        }else if (tmpoldstatid.getText().toString().equals("3")){
            tmpnewstatid.setText("4");
            submittrans.setText("Submit Penerimaan");//pelelang dan penjual tidak bisa
        }
    }

    private void btnsetting(){
        if (tmpidpllg.getText().toString().equals(userid) && (tmpoldstatid.getText().toString().equals("1"))){
            submittrans.setVisibility(View.INVISIBLE);
        }else if (tmpidpsrt.getText().toString().equals(userid) && (tmpoldstatid.getText().toString().equals("2"))){
            submittrans.setVisibility(View.INVISIBLE);
        }else if (tmpidpllg.getText().toString().equals(userid) && (tmpoldstatid.getText().toString().equals("3"))){
            submittrans.setVisibility(View.INVISIBLE);
        }else if (tmpidpsrt.getText().toString().equals(userid) && (tmpoldstatid.getText().toString().equals("3"))) {
            submittrans.setVisibility(View.INVISIBLE);
            submitfinish.setVisibility(View.VISIBLE);
        }else if (tmpoldstatid.getText().toString().equals("4")){
            submitfinish.setVisibility(View.INVISIBLE);
        }
    }

    private void setnewstat(){
        if (tmpoldstat.getText().toString().equals("Belum Dibayarkan")){
            tmpnewstat.setText("Dibayarkan");
        }else if (tmpoldstat.getText().toString().equals("Dibayarkan")){
            tmpnewstat.setText("Hewan Dikirim");
        }else if (tmpoldstat.getText().toString().equals("Hewan Dikirim")){
            tmpnewstat.setText("Hewan Diterima");
        }
    }

    private void getnamapemenang(){
        String pemenangkey = tmpidpsrt.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetalldatauserbyidResponse> call = service.getalldatauserbyid(
                pemenangkey
        );

        call.enqueue(new Callback<GetalldatauserbyidResponse>() {
            @Override
            public void onResponse(Call<GetalldatauserbyidResponse> call, Response<GetalldatauserbyidResponse> response) {
                if (response.isSuccessful()){
                    usrnamepemenang.setText(response.body().getSuccess().getName());
                }
            }

            @Override
            public void onFailure(Call<GetalldatauserbyidResponse> call, Throwable t) {

            }
        });
    }

    private void getnamapllg(){
        String pllgkey = tmpidpllg.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetalldatauserbyidResponse> call = service.getalldatauserbyid(
                pllgkey
        );

        call.enqueue(new Callback<GetalldatauserbyidResponse>() {
            @Override
            public void onResponse(Call<GetalldatauserbyidResponse> call, Response<GetalldatauserbyidResponse> response) {
                if (response.isSuccessful()){
                    usrnamepllg.setText(response.body().getSuccess().getName());
                }
            }

            @Override
            public void onFailure(Call<GetalldatauserbyidResponse> call, Throwable t) {

            }
        });
    }

    private void btnupdatetrans(){

        String iddettranskey = tmpiddettranslel.getText().toString().trim();
        String newstatidkey = tmpnewstatid.getText().toString().trim();
        String newstatkey = tmpnewstat.getText().toString().trim();
        String imgparentaa = tmpimgparenttoken.getText().toString().trim();
        String imgparnetbb = tmpoldstatid.getText().toString().trim();
        String imgparentbuktitranss = imgparentaa+imgparnetbb;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<UpdateleltransstatResponse> call = service.ubahstattrans(
                iddettranskey,
                imgparentbuktitranss,
                newstatidkey,
                newstatkey

        );

        call.enqueue(new Callback<UpdateleltransstatResponse>() {
            @Override
            public void onResponse(Call<UpdateleltransstatResponse> call, Response<UpdateleltransstatResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailTransaksiLelang.this, "Status Transaksi Diubah", Toast.LENGTH_SHORT).show();
                    touploadbuktitrans();
                }
            }

            @Override
            public void onFailure(Call<UpdateleltransstatResponse> call, Throwable t) {
                Toast.makeText(DetailTransaksiLelang.this, "cek ada yg kurang", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void touploadbuktitrans(){
        String imgparenta = tmpimgparenttoken.getText().toString().trim();
        String imgparnetb = tmpoldstatid.getText().toString().trim();
        String idpelelangforrating = tmpidpllg.getText().toString().trim();
        String imgparentbuktitrans = imgparenta+imgparnetb;

        Intent intent = new Intent(DetailTransaksiLelang.this, ImageUploadActivity.class);
        intent.putExtra("imgbuktitrans", imgparentbuktitrans );
        intent.putExtra("idpelelangforrating",idpelelangforrating );
        startActivity(intent);
    }

    private void tmbhsaldopelelang(){
        String pelelangkey = tmpidpllg.getText().toString().trim();
        String nilaikey = nilaiakhir.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<TopupResponse> call = service.saldotambah(
                pelelangkey,nilaikey
        );
        call.enqueue(new Callback<TopupResponse>() {
            @Override
            public void onResponse(Call<TopupResponse> call, Response<TopupResponse> response) {
                if (response.isSuccessful()){

                }
            }
            @Override
            public void onFailure(Call<TopupResponse> call, Throwable t) {

            }
        });
    }

    private void tambahratingnreview(){

    }
}