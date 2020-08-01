package com.example.tryretrofitlogin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.models.Photo;

import java.io.File;
import java.io.IOException;


public class ImageUploadActivity extends AppCompatActivity {

    private Button btnPickgallery, btnCaptureimg;
    private ImageView imageDiplay;
    public static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        btnCaptureimg = (Button) findViewById(R.id.btn_captureImage);
        btnPickgallery = (Button)findViewById(R.id.btn_pickgallery);
        imageDiplay = (ImageView) findViewById(R.id.imagedisplay);

        btnCaptureimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImg();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageDiplay.setImageBitmap(bitmap);
    }

    private void captureImg() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photofile = null;
            try{
                photofile = createImagefile();
            } catch (IOException ex){
                Toast.makeText(this, "foto tidak bisa dibuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
            if (photofile!=null){
                Uri photoURI = FileProvider.getUriForFile(this,getPackageName() +".jpg",photofile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImagefile() throws IOException{
        String imgFilename = "img";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imgFilename, ".jpg",storageDir);
        return image;
    }

}