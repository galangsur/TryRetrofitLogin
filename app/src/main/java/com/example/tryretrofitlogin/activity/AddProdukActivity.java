package com.example.tryretrofitlogin.activity;

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
import com.example.tryretrofitlogin.postresponse.addlelangberlangsung.AddlelbrlangsungResponse;
import com.example.tryretrofitlogin.postresponse.addproduk.AddprodukResponse;
import com.example.tryretrofitlogin.responses.gethewan.HewanResponse;
import com.example.tryretrofitlogin.responses.gethewan.SuccessItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProdukActivity extends AppCompatActivity {

    private EditText edttxtcomment, edttxtharga, edttxtjmlhhwn;
    private TextView txtuserid,txtnamauser, txtimgtokenprdk;
    private Spinner spinhewanid;
    private Button btnaddprdk;
    private ImageView btnback;
    private String userid, jenishewan, groupname, prdkimgparent, namauser;
    private int idhewan;
    public List<SuccessItem> hewans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produk);

        txtuserid = (TextView) findViewById(R.id.txtTextuserIdprdk);
        txtnamauser = (TextView) findViewById(R.id.txtTextNamauserprdk);
        txtimgtokenprdk = (TextView) findViewById(R.id.prdkimgtoken);
        spinhewanid = (Spinner) findViewById(R.id.spinHewanIdprdk);
        edttxtcomment = (EditText) findViewById(R.id.editTextcommentprdk);
        edttxtharga = (EditText) findViewById(R.id.editTexthargaprdk);
        edttxtjmlhhwn = (EditText) findViewById(R.id.editTextjmlhprdk);
        btnaddprdk = (Button) findViewById(R.id.btn_addprdk);
        btnback = (ImageView) findViewById(R.id.btn_backtolist);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        namauser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        txtuserid.setText(userid);
        txtnamauser.setText(namauser);
        txtimgtokenprdk.setText(prdkimgparent());
        prdkimgparent = txtimgtokenprdk.getText().toString().trim();

        initSpinnerHewan();
        spinhewanid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHewan = parent.getItemAtPosition(position).toString();
                idhewan = hewans.get(position).getId();
                jenishewan = hewans.get(position).getJenis();
                Toast.makeText(AddProdukActivity.this, selectedHewan + "di pilih dengan id" + idhewan +"jenis " +jenishewan, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnaddprdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadproduk();
            }
        });
    }

    private void uploadproduk(){
        String prdk_iduser = txtuserid.getText().toString().trim();
        String prdk_lelcomment = edttxtcomment.getText().toString().trim();
        String prdk_lelharga = edttxtharga.getText().toString().trim();
        String prdk_jmlh = edttxtjmlhhwn.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddprodukResponse> call = service.createproduk(
                prdk_iduser, idhewan, prdk_lelcomment, prdk_jmlh, prdk_lelharga);

        call.enqueue(new Callback<AddprodukResponse>() {
            @Override
            public void onResponse(Call<AddprodukResponse> call, Response<AddprodukResponse> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(AddProdukActivity.this, "yesujses", Toast.LENGTH_SHORT).show();
                    touploadimg();
                }
            }

            @Override
            public void onFailure(Call<AddprodukResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddProdukActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinhewanid.setAdapter(adapter);
                }else {
                    Toast.makeText(AddProdukActivity.this, "gagal memilih hewan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HewanResponse> call, Throwable t) {
                Toast.makeText(AddProdukActivity.this, "koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void touploadimg(){
        Intent intent = new Intent(AddProdukActivity.this, Uploadhewan.class);
        intent.putExtra("imghw", prdkimgparent);
        startActivity(intent);
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    private static String prdkimgparent(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }
}