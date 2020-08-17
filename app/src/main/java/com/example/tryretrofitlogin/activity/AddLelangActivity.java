package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.LelBerlangsung;
import com.example.tryretrofitlogin.models.Lelang;
import com.example.tryretrofitlogin.postresponse.addlelang.AddLelangResponse;
import com.example.tryretrofitlogin.postresponse.addlelangberlangsung.AddlelbrlangsungResponse;
import com.example.tryretrofitlogin.responses.gethewan.HewanResponse;
import com.example.tryretrofitlogin.responses.gethewan.SuccessItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddLelangActivity extends AppCompatActivity {

    private EditText edttxtcomment, edttxtharga;
    private TextView txtuserid;
    private Spinner spinhewanid;
    private Button btnaddlelang;
    private ImageView btnback;
    private String userid, jenishewan, groupname;
    private int idhewan;
    public List<SuccessItem> hewans;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lelang);

        txtuserid = (TextView) findViewById(R.id.txtTextuserId);
        spinhewanid = (Spinner) findViewById(R.id.spinHewanId);
        edttxtcomment = (EditText) findViewById(R.id.editTextcomment);
        edttxtharga = (EditText) findViewById(R.id.editTextharga);
        btnaddlelang = (Button) findViewById(R.id.btn_addlelang);
        btnback = (ImageView) findViewById(R.id.btn_backtohome);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        txtuserid.setText(userid);

        rootRef = FirebaseDatabase.getInstance().getReference();

        initSpinnerHewan();
        spinhewanid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHewan = parent.getItemAtPosition(position).toString();
                idhewan = hewans.get(position).getId();
                jenishewan = hewans.get(position).getJenis();
                Toast.makeText(AddLelangActivity.this, selectedHewan + "di pilih dengan id" + idhewan +"jenis " +jenishewan, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnaddlelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLelangActivity.this, "btntst", Toast.LENGTH_SHORT).show();
                uploadLelbrjalan();
                uploadLelang();

//                newGroup(groupname);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initSpinnerHewan(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        final Call<HewanResponse> call = service.gethewan();

        call.enqueue(new Callback<HewanResponse>() {
            @Override
            public void onResponse(Call<HewanResponse> call, Response<HewanResponse> response) {
                if (response.isSuccessful()) {
                    hewans = response.body().getSuccess();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < hewans.size(); i++){
                        listSpinner.add(hewans.get(i).getJenis());
                    }

                    //mengoper data ke spinner dengan adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddLelangActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinhewanid.setAdapter(adapter);
                }else {
                    Toast.makeText(AddLelangActivity.this, "gagal memilih hewan cuok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HewanResponse> call, Throwable t) {
                Toast.makeText(AddLelangActivity.this, "koneksi cuok , koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadLelang(){
        String iduser = txtuserid.getText().toString().trim();
        String lelcomment = edttxtcomment.getText().toString().trim();
        String lelharga = edttxtharga.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Lelang lelang = new Lelang(iduser, idhewan, lelharga, lelcomment);

        Call<AddLelangResponse> call = service.createLelang(
                iduser, idhewan, lelharga, lelcomment
        );

        call.enqueue(new Callback<AddLelangResponse>() {
            @Override
            public void onResponse(Call<AddLelangResponse> call, Response<AddLelangResponse> response) {
                if (response.isSuccessful()){

                    Toast.makeText(AddLelangActivity.this, "respon" + response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AddLelangResponse> call, Throwable t) {

            }
        });


    }

    private void uploadLelbrjalan(){
        Toast.makeText(this, "fncttst", Toast.LENGTH_SHORT).show();
//        groupname = "Belum Mulai";
        String lelbr_iduser = txtuserid.getText().toString().trim();
        String lelbr_lelcomment = edttxtcomment.getText().toString().trim();
        String lelbr_lelharga = edttxtharga.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddlelbrlangsungResponse> call = service.lelbrlngsung(
                lelbr_iduser, idhewan, lelbr_lelcomment, lelbr_lelharga, lelbr_lelcomment);

        call.enqueue(new Callback<AddlelbrlangsungResponse>() {
            @Override
            public void onResponse(Call<AddlelbrlangsungResponse> call, Response<AddlelbrlangsungResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddLelangActivity.this, "ddf" +response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddlelbrlangsungResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void newGroup(String groupname){

        rootRef.child("Groups").child(groupname).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
