package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.tryretrofitlogin.postresponse.addReqlelangtoadmin.AddReqlelangtoadminResponse;
import com.example.tryretrofitlogin.postresponse.addlelangberlangsung.AddlelbrlangsungResponse;
import com.example.tryretrofitlogin.postresponse.addphoto.PostphotoResponse;
import com.example.tryretrofitlogin.putresponse.kurangisaldo.KurangisaldoResponse;
import com.example.tryretrofitlogin.responses.gethewan.HewanResponse;
import com.example.tryretrofitlogin.responses.gethewan.SuccessItem;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnSelectedListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddLelangActivity extends AppCompatActivity {

    private EditText edttxtcomment, edttxtharga,edttxtwarnabulu,edttxtumurhewan;
    private TextView txtuserid,txtusernama, txtimgtokenlel,pilihtxthargaperbid,pilihtxtwktdisply,tmpidhewan,
            pilihtxtwkttrue,txtsaldocek,txtsaldokurang,txtsertiftokenlel,txtwarnabulu;
    private Spinner spinhewanid,spinsatuanumurid, spinjensikelaminid;
    private Button btnaddlelang, btnaddsertif, btn_bidwaktuA, btn_bidwaktuB, btn_bidwaktuC,
            btn_bidhargaA, btn_bidhargaB, btn_bidhargaC;
    private ImageView btnback;
    private String userid, jenishewan, groupname, lelimgparent,lelsertifparent, usernama, usersaldo;
    private String displaywktA, displaywktB, displaywktC, truewktA, truewktB, truewktC;
    private String harga_a, harga_b, harga_c;
    private int idhewan,saldoWallet;
    public List<SuccessItem> hewans;
    private DatabaseReference rootRef;

    //untuk image picker
    private ImageView fotoDiplay, btnPickfoto, fotosertifDisplay, btnPickfotosertif;
    private File attachment,attachsertif;
    private TextView eventAttachment,imgparent,sertifAttachment,txtpesertaonlinetoken;
    private String imgparenthw,fotoprtkey;

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getApplicationContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private String getRealPathsertifFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getApplicationContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lelang);

        txtuserid = (TextView) findViewById(R.id.txtTextuserId);
        txtusernama = (TextView) findViewById(R.id.txtTextuserNama);
        txtimgtokenlel = (TextView) findViewById(R.id.lelimgtoken);
        txtsaldocek = (TextView) findViewById(R.id.usersaldocek);
        txtsaldokurang = (TextView) findViewById(R.id.txtsaldokurang);
        txtwarnabulu = (TextView) findViewById(R.id.titleTextwarnabulu);
        txtpesertaonlinetoken = (TextView) findViewById(R.id.pesertaonlinetoken);
        tmpidhewan = (TextView) findViewById(R.id.tmpstringidhewan);
        spinhewanid = (Spinner) findViewById(R.id.spinHewanId);
        spinsatuanumurid = (Spinner) findViewById(R.id.spinumursatuanId);
        spinjensikelaminid = (Spinner) findViewById(R.id.spinjensikelaminId);
        edttxtumurhewan = (EditText) findViewById(R.id.editTextumur);
        edttxtcomment = (EditText) findViewById(R.id.editTextcomment);
        edttxtharga = (EditText) findViewById(R.id.editTextharga);
        edttxtwarnabulu = (EditText) findViewById(R.id.editTextwarnabulu);
        pilihtxtwktdisply = (TextView) findViewById(R.id.txt_waktubid);
        pilihtxthargaperbid = (TextView) findViewById(R.id.txt_jmlhperbid);
        pilihtxtwkttrue = (TextView) findViewById(R.id.waktutruepick);
        btnaddlelang = (Button) findViewById(R.id.btn_addlelang);
        btnaddsertif = (Button) findViewById(R.id.btn_addfotosertif);
        btnback = (ImageView) findViewById(R.id.btn_backtohome);
        btn_bidwaktuA = (Button)findViewById(R.id.btn_waktu1);
        btn_bidwaktuB = (Button)findViewById(R.id.btn_waktu2);
        btn_bidwaktuC = (Button)findViewById(R.id.btn_waktu3);
        btn_bidhargaA = (Button)findViewById(R.id.btn_bid1);
        btn_bidhargaB = (Button)findViewById(R.id.btn_bid2);
        btn_bidhargaC = (Button)findViewById(R.id.btn_bid3);
        displaywktA = "5";
        displaywktB = "15";
        displaywktC = "30";
        truewktA = "5000";
        truewktB = "15000";
        truewktC = "30000";
        harga_a = "25000";
        harga_b = "50000";
        harga_c = "100000";

        //untuk image picker
        btnPickfoto = (ImageView)findViewById(R.id.btn_pickfotohw);
        fotoDiplay = (ImageView) findViewById(R.id.fotodisplayhw);
        eventAttachment = (TextView) findViewById(R.id.fotoattachmenturi);

        btnPickfotosertif = (ImageView) findViewById(R.id.btn_pickfotosertif);
        fotosertifDisplay = (ImageView) findViewById(R.id.sertifdisplayhw);
        sertifAttachment = (TextView) findViewById(R.id.sertifattachmenturi);
        txtsertiftokenlel = (TextView) findViewById(R.id.lelsertifimgtoken);

        userid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        usernama = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();
        txtuserid.setText(userid);
        txtusernama.setText(usernama);

        //get randomkey id for image
        txtimgtokenlel.setText(lelimgparent());
        txtsertiftokenlel.setText(lelsertifparent());

        //get randomkey id for pesertaonlinemanager
        txtpesertaonlinetoken.setText(pesertaonlinetoken());


//        lelsertifparent = txtsertiftokenlel.getText().toString().trim();

        rootRef = FirebaseDatabase.getInstance().getReference();
        ceksaldo();

        initSpinnerHewan();
        spinhewanid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHewan = parent.getItemAtPosition(position).toString();
                idhewan = hewans.get(position).getId();
                String hewanid = String.valueOf(idhewan);
                tmpidhewan.setText(hewanid);
                jenishewan = hewans.get(position).getJenis();
                Toast.makeText(AddLelangActivity.this, selectedHewan + "di pilih dengan id" + idhewan +"jenis " +jenishewan, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinsatuanumurid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnaddlelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kurangisaldo();
                uploadLelang(attachment);

//                newGroupFirebase(groupname);
            }
        });

        btnaddsertif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImg(attachsertif);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnPickfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImgfromDevice();

            }
        });

        btnPickfotosertif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImgsertiffromDevice();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_bidwaktuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxtwktdisply.setText(displaywktA);
                pilihtxtwkttrue.setText(truewktA);
            }
        });
        btn_bidwaktuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxtwktdisply.setText(displaywktB);
                pilihtxtwkttrue.setText(truewktB);
            }
        });
        btn_bidwaktuC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxtwktdisply.setText(displaywktC);
                pilihtxtwkttrue.setText(truewktC);
            }
        });

        btn_bidhargaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxthargaperbid.setText(harga_a);
            }
        });
        btn_bidhargaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxthargaperbid.setText(harga_b);
            }
        });
        btn_bidhargaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihtxthargaperbid.setText(harga_c);
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

    private void uploadLelang(File attachment){
        String umursatuan = spinsatuanumurid.getSelectedItem().toString().trim();
        String umurinput = edttxtumurhewan.getText().toString().trim();
        String umurHewangabung = umursatuan+umurinput;
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),attachment);

            RequestBody iduser = RequestBody.create(MediaType.parse("text/plain"),txtuserid.getText().toString());
            RequestBody idhewan = RequestBody.create(MediaType.parse("text/plain"),tmpidhewan.getText().toString());
            RequestBody lelharga = RequestBody.create(MediaType.parse("text/plain"),edttxtharga.getText().toString());
            RequestBody lelcomment = RequestBody.create(MediaType.parse("text/plain"),edttxtcomment.getText().toString().trim());
            MultipartBody.Part imgtoken =  MultipartBody.Part.createFormData("img_lelang",attachment.getName(),requestBody);
            RequestBody umur_hewan = RequestBody.create(MediaType.parse("text/plain"),umurHewangabung);
            RequestBody jeniskelamin_hewan = RequestBody.create(MediaType.parse("text/plain"),spinjensikelaminid.getSelectedItem().toString());
            RequestBody warna_hewan = RequestBody.create(MediaType.parse("text/plain"),edttxtwarnabulu.getText().toString().trim());
            RequestBody pesertaonline_token = RequestBody.create(MediaType.parse("text/plain"),txtpesertaonlinetoken.getText().toString().trim());
            RequestBody lelimgparent = RequestBody.create(MediaType.parse("text/plain"),txtimgtokenlel.getText().toString().trim());
            RequestBody lelsertifparent = RequestBody.create(MediaType.parse("text/plain"),txtsertiftokenlel.getText().toString().trim());
            RequestBody reqwaktuperbid = RequestBody.create(MediaType.parse("text/plain"),pilihtxtwkttrue.getText().toString().trim());
            RequestBody reqnominalperbid = RequestBody.create(MediaType.parse("text/plain"),pilihtxthargaperbid.getText().toString().trim());

            Toast.makeText(this, "tosat"+iduser+idhewan+lelharga+lelcomment+reqwaktuperbid+reqnominalperbid+imgtoken , Toast.LENGTH_SHORT).show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIService service = retrofit.create(APIService.class);

            Call<AddReqlelangtoadminResponse> call = service.requestLelangtoAdmin(
                    iduser, idhewan, lelharga, lelcomment, imgtoken,umur_hewan,jeniskelamin_hewan,warna_hewan, lelimgparent,
                    pesertaonline_token,lelsertifparent, reqwaktuperbid, reqnominalperbid
            );

            call.enqueue(new Callback<AddReqlelangtoadminResponse>() {
                @Override
                public void onResponse(Call<AddReqlelangtoadminResponse> call, Response<AddReqlelangtoadminResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(AddLelangActivity.this, "suskes", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddReqlelangtoadminResponse> call, Throwable t) {

                }
            });
    }

//        String param1 = txtuserid.getText().toString().trim();
//        String param2 = String.valueOf(idhewan);
//        String param3 = edttxtharga.getText().toString().trim();
//        String param4 = edttxtcomment.getText().toString().trim();
//        String param6 = pilihtxtwkttrue.getText().toString().trim();
//        String param7 = pilihtxthargaperbid.getText().toString().trim();

    private void uploadLelbrjalan() {
//        Toast.makeText(this, "fncttst", Toast.LENGTH_SHORT).show();
        groupname = "Belum Mulai";
        String psrtonline_token = txtpesertaonlinetoken.getText().toString().trim();
        String lelbr_iduser = txtuserid.getText().toString().trim();
        String lelbr_lelcomment = edttxtcomment.getText().toString().trim();
        String lelbr_lelharga = edttxtharga.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<AddlelbrlangsungResponse> call = service.lelbrlngsung(
                lelbr_iduser, idhewan, lelbr_lelcomment, lelbr_lelharga, groupname, psrtonline_token);

        call.enqueue(new Callback<AddlelbrlangsungResponse>() {
            @Override
            public void onResponse(Call<AddlelbrlangsungResponse> call, Response<AddlelbrlangsungResponse> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<AddlelbrlangsungResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void newGroupFirebase(String groupname){

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

    private static final String ALLOWED_SERTIFCHARACTERS ="qwertyuiopasdfghjklzxcvbnm";
    private static String lelsertifparent(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(ALLOWED_SERTIFCHARACTERS.charAt(random.nextInt(ALLOWED_SERTIFCHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

    private static final String PESERTAONLINETOKEN_CHARACTERS ="qwertyuiopasdfghjklzxcvbnm0123456789";
    private static String pesertaonlinetoken(){
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++){
            stringBuilder.append(PESERTAONLINETOKEN_CHARACTERS.charAt(random.nextInt(PESERTAONLINETOKEN_CHARACTERS.length())));
        }

        return stringBuilder.toString();
    }

    private void touploadimg(){
        Intent intent = new Intent(AddLelangActivity.this, Uploadhewan.class);
        lelimgparent = txtimgtokenlel.getText().toString().trim();

        intent.putExtra("imghw", lelimgparent);
        intent.putExtra("sertifhw", lelsertifparent);
        startActivity(intent);
    }

    private void kurangisaldo(){
        String user_id = txtuserid.getText().toString().trim();
        String pengurang = "300000";
//        String nominal = saldotmbh.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<KurangisaldoResponse> call = service.saldokurang(
                user_id ,pengurang);

        call.enqueue(new Callback<KurangisaldoResponse>() {
            @Override
            public void onResponse(Call<KurangisaldoResponse> call, Response<KurangisaldoResponse> response) {
                if (response.isSuccessful()){
                    uploadLelbrjalan();
                }

            }

            @Override
            public void onFailure(Call<KurangisaldoResponse> call, Throwable t) {
                Toast.makeText(AddLelangActivity.this, "Saldo anda kurang", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void ceksaldo(){
        String userwoi = txtuserid.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetWalletInfoResponse> call = service.getinfosaldo(userwoi,usersaldo);

        call.enqueue(new Callback<GetWalletInfoResponse>() {
            @Override
            public void onResponse(Call<GetWalletInfoResponse> call, Response<GetWalletInfoResponse> response) {
                saldoWallet = response.body().getSaldo();
                String saldo = String.valueOf(saldoWallet);
                txtsaldocek.setText(saldo);

                if (saldoWallet >= 300000){
                    txtsaldokurang.setVisibility(View.INVISIBLE);
                }else if (saldoWallet <= 300000){
                    btnaddlelang.setVisibility(View.INVISIBLE);
                    btnaddlelang.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<GetWalletInfoResponse> call, Throwable t) {

            }
        });
    }

    //untuk imagepicker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        Bitmap sertifbitmap = (Bitmap)data.getExtras().get("sertifdata");
        fotoDiplay.setImageBitmap(bitmap);
        fotosertifDisplay.setImageBitmap(sertifbitmap);

//        if (fotoDiplay.getDrawable().equals(bitmap)&&(fotosertifDisplay.getDrawable().equals(sertifbitmap))){
//            txtimgtokenlel.setText(lelimgparent());
//            lelimgparent = txtimgtokenlel.getText().toString().trim();
//            txtsertiftokenlel.setText(lelsertifparent());
//            lelsertifparent = txtsertiftokenlel.getText().toString().trim();
//            Toast.makeText(this, "gambar ada", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "kosong gan", Toast.LENGTH_SHORT).show();
//        }

    }

    private void selectImgfromDevice(){
        TedImagePicker.with(AddLelangActivity.this)
                .start(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NotNull Uri uri) {
                        attachment  = new File(getRealPathFromURI(uri));
                        eventAttachment.setText(attachment.getName());

                        Picasso.with(AddLelangActivity.this)
                                .load(uri)
                                .fit()
                                .centerInside()
                                .into(fotoDiplay);

                        Toast.makeText(AddLelangActivity.this, "namafile"+eventAttachment+attachment, Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void selectImgsertiffromDevice(){
        TedImagePicker.with(AddLelangActivity.this)
                .start(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NotNull Uri urisertif) {
                        attachsertif  = new File(getRealPathsertifFromURI(urisertif));
                        sertifAttachment.setText(attachsertif.getName());

                        Picasso.with(AddLelangActivity.this)
                                .load(urisertif)
                                .fit()
                                .centerInside()
                                .into(fotosertifDisplay);

                        Toast.makeText(AddLelangActivity.this, "namafile"+sertifAttachment+attachsertif, Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void uploadImg(File attachmentsertif){
//        Toast.makeText(this, "tastos", Toast.LENGTH_SHORT).show();
        RequestBody requestsertifBody = RequestBody.create(MediaType.parse("*/*"),attachmentsertif);
        String a = "3";
        String b = "5";
        String ImgParent = txtsertiftokenlel.getText().toString().trim();


        MultipartBody.Part photo =  MultipartBody.Part.createFormData("photo",attachmentsertif.getName(),requestsertifBody);
        RequestBody imgparent = RequestBody.create(MediaType.parse("multipart/form-data"),ImgParent);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<PostphotoResponse> call = service.postPhoto(
                imgparent,photo
        );

        call.enqueue(new Callback<PostphotoResponse>() {
            @Override
            public void onResponse(Call<PostphotoResponse> call, Response<PostphotoResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddLelangActivity.this, "sertif masuk", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostphotoResponse> call, Throwable t) {
                Toast.makeText(AddLelangActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });


    }
}


//cara nyimpan imgparent, jalanin lelimgparent dulu biar dapat idimgparent baru nnti get dia sebagai key untuk ngeget img
