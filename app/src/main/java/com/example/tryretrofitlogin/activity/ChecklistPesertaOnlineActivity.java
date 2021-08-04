package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Pesertaonlineadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan.Getlelpesertabyidlelberjalan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChecklistPesertaOnlineActivity extends AppCompatActivity {
    private RecyclerView rvChecklistpesertaOnline;
    private TextView tmpIdlelberjalan, tmpPesertaonlinetoken;
    private ImageView btnBack;
    private String lelberjalanid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_peserta_online);

        rvChecklistpesertaOnline = (RecyclerView) findViewById(R.id.RV_checklistpesertaonline);
        tmpIdlelberjalan = (TextView)findViewById(R.id.tmp_idlelbrjalanchecklistpesertaonline);
        tmpPesertaonlinetoken = (TextView)findViewById(R.id.tmppesertaonline_token);
        btnBack = (ImageView)findViewById(R.id.btn_backchecklistpeserta);

        Intent intenthewan = getIntent();
        lelberjalanid = intenthewan.getStringExtra("iduntukgetpesertaonline");
        tmpIdlelberjalan.setText(lelberjalanid);
        Toast.makeText(this, lelberjalanid, Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        rvChecklistpesertaOnline.setLayoutManager(layoutManager);
        rvChecklistpesertaOnline.setHasFixedSize(true);

        getPesertaonline();
    }

    private void getPesertaonline(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Getlelpesertabyidlelberjalan> call = service.getLelpesertabyidlelberjalan(
                lelberjalanid);

        call.enqueue(new Callback<Getlelpesertabyidlelberjalan>() {
            @Override
            public void onResponse(Call<Getlelpesertabyidlelberjalan> call, Response<Getlelpesertabyidlelberjalan> response) {
                Pesertaonlineadapt pesertaonlineAdapt = new Pesertaonlineadapt(ChecklistPesertaOnlineActivity.this,response.body().getSuccess());
                pesertaonlineAdapt.notifyDataSetChanged();
                rvChecklistpesertaOnline.setAdapter(pesertaonlineAdapt);
            }

            @Override
            public void onFailure(Call<Getlelpesertabyidlelberjalan> call, Throwable t) {

            }
        });
    }
}