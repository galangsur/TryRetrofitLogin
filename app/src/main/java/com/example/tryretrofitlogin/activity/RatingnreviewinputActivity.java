package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.ListlelpesertaAdapt;
import com.example.tryretrofitlogin.adapter.Ratingnreviewadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.GetLelangbyHewanResponse;
import com.example.tryretrofitlogin.postresponse.addratingnreview.Addratingnreview;
import com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan.Getlelpesertabyidlelberjalan;
import com.example.tryretrofitlogin.responses.getratingreviewbytoken.Getreviewratingbytoken;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingnreviewinputActivity extends AppCompatActivity {

    private AlertDialog.Builder dialoglistratingnreview,dialoglistpesertalelang;
    private AlertDialog dialogratingnreview,dialogpesertalelang;
    private RatingBar ratingBar;
    private EditText edttxtReview;
    private RecyclerView rvRatingnreview, rvListpesertalelang;
    private Button btnRatingnreview,btnLihatrating,btnLihatlistpeserta;
    private Float ratingnumber;
    private String ratingstring,review;
    private TextView txtRatingnreviewtoken,tmpKeyrnrtoken, tmpKeyidberjalan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingnreviewinput);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        edttxtReview = (EditText) findViewById(R.id.edtxt_review);
        btnRatingnreview = (Button) findViewById(R.id.btn_ratingnreview);
        btnLihatrating = (Button) findViewById(R.id.btn_lihatrating);
        btnLihatlistpeserta = (Button) findViewById(R.id.btn_listpeserta);
        txtRatingnreviewtoken = (TextView) findViewById(R.id.txt_reviewnratingtoken);


        txtRatingnreviewtoken.setText(ratingnreviewtoken());

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnRatingnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addreviewnrating();
            }
        });

        btnLihatrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglistratingnreview();
            }
        });

        btnLihatlistpeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglistpeserta();
            }
        });


    }

    private void dialoglistratingnreview(){
        dialoglistratingnreview = new AlertDialog.Builder(RatingnreviewinputActivity.this);
        final View listratingnreviewView = getLayoutInflater().inflate(R.layout.dialog_listratingnreview,null);

        tmpKeyrnrtoken = (TextView) listratingnreviewView.findViewById(R.id.txt_reviewnratingtoken);
        rvRatingnreview = (RecyclerView) listratingnreviewView.findViewById(R.id.RV_ratingnreview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        rvRatingnreview.setLayoutManager(layoutManager);
        rvRatingnreview.setHasFixedSize(true);

        dialoglistratingnreview.setView(listratingnreviewView);
        dialogratingnreview = dialoglistratingnreview.create();
        dialogratingnreview.show();

        getreviewnrating();
    }

    private void dialoglistpeserta(){
        dialoglistpesertalelang = new AlertDialog.Builder(RatingnreviewinputActivity.this);
        final View listpesertalelangView = getLayoutInflater().inflate(R.layout.dialog_listpeserta,null);

        tmpKeyidberjalan = (TextView) listpesertalelangView.findViewById(R.id.tmpkeyidberjalantoken);
        rvListpesertalelang = (RecyclerView) listpesertalelangView.findViewById(R.id.RV_listpeserta);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        rvListpesertalelang.setLayoutManager(layoutManager);
        rvListpesertalelang.setHasFixedSize(true);

        dialoglistpesertalelang.setView(listpesertalelangView);
        dialogpesertalelang = dialoglistpesertalelang.create();
        dialogpesertalelang.show();

        getlistpesertalelang();
    }

    private void getreviewnrating(){
        String rnrtoken = "sadasfasf";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Getreviewratingbytoken> call = service.getreviewratingbytoken(
                rnrtoken);

        call.enqueue(new Callback<Getreviewratingbytoken>() {
            @Override
            public void onResponse(Call<Getreviewratingbytoken> call, Response<Getreviewratingbytoken> response) {
                Ratingnreviewadapt ratingnreviewadapt = new Ratingnreviewadapt(RatingnreviewinputActivity.this,response.body().getSuccess());
                ratingnreviewadapt.notifyDataSetChanged();
                rvRatingnreview.setAdapter(ratingnreviewadapt);
            }

            @Override
            public void onFailure(Call<Getreviewratingbytoken> call, Throwable t) {

            }
        });
    }

    private void getlistpesertalelang(){
        String idberjalan = "1";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Getlelpesertabyidlelberjalan> call = service.getLelpesertabyidlelberjalan(
                idberjalan);

        call.enqueue(new Callback<Getlelpesertabyidlelberjalan>() {
            @Override
            public void onResponse(Call<Getlelpesertabyidlelberjalan> call, Response<Getlelpesertabyidlelberjalan> response) {
                ListlelpesertaAdapt listlelpesertaAdapt = new ListlelpesertaAdapt(RatingnreviewinputActivity.this,response.body().getSuccess());
                listlelpesertaAdapt.notifyDataSetChanged();
                rvListpesertalelang.setAdapter(listlelpesertaAdapt);
            }

            @Override
            public void onFailure(Call<Getlelpesertabyidlelberjalan> call, Throwable t) {

            }
        });
    }

    private void addreviewnrating(){
        String rnrtoken = txtRatingnreviewtoken.getText().toString().trim();
        ratingnumber = ratingBar.getRating();
        review = edttxtReview.getText().toString().trim();
        String namareview = "jagir";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call <Addratingnreview> call = service.addratingnreview(
                rnrtoken,namareview,ratingnumber,review
        );

        call.enqueue(new Callback<Addratingnreview>() {
            @Override
            public void onResponse(Call<Addratingnreview> call, Response<Addratingnreview> response) {
                Toast.makeText(RatingnreviewinputActivity.this, "pas", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Addratingnreview> call, Throwable t) {
                Toast.makeText(RatingnreviewinputActivity.this, "ad yg kurang", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private static String ratingnreviewtoken(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

}