package com.example.tryretrofitlogin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.postresponse.addphoto.PostphotoResponse;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.io.File;

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

public class ImageUploadtwoActivity extends AppCompatActivity {

    private Button btnUploadimg;
    private ImageView imageDiplay, btnPickgallery;
    private TextView eventAttachment,txt_pickcount;
    private File attachment;
    public static final int REQUEST_IMAGE_CAPTURE = 1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_uploadtwo);

        btnUploadimg = (Button) findViewById(R.id.btn_imguploadtwo);
        btnPickgallery = (ImageView)findViewById(R.id.btn_pickgallery);
        imageDiplay = (ImageView) findViewById(R.id.imagedisplaytwo);
        eventAttachment = (TextView) findViewById(R.id.imageUri);
        txt_pickcount = (TextView)findViewById(R.id.pickcount);

        btnUploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageUploadtwoActivity.this, "tos", Toast.LENGTH_SHORT).show();
                uploadImg(attachment);
            }
        });

        btnPickgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImgfromDevice();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageDiplay.setImageBitmap(bitmap);
    }

    private void selectImgfromDevice(){
        TedImagePicker.with(ImageUploadtwoActivity.this)
                .start(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NotNull Uri uri) {
                        attachment  = new File(getRealPathFromURI(uri));
                        eventAttachment.setText(attachment.getName());

                        Picasso.with(ImageUploadtwoActivity.this)
                                .load(uri)
                                .fit()
                                .centerInside()
                                .into(imageDiplay);
                    }
                });
    }

    private void uploadImg(File attachment){
        Toast.makeText(this, "tastos", Toast.LENGTH_SHORT).show();
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),attachment);
        String a = "3";
        String b = "5";
        String Imgparent = a+b;


        MultipartBody.Part photo =  MultipartBody.Part.createFormData("photo",attachment.getName(),requestBody);
        RequestBody imgparent = RequestBody.create(MediaType.parse("multipart/form-data"),Imgparent);


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
                Toast.makeText(ImageUploadtwoActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostphotoResponse> call, Throwable t) {
                Toast.makeText(ImageUploadtwoActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void pickcountthree(){
        txt_pickcount.setText("2");
    }
}