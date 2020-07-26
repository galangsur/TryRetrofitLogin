package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.HorizImageRVadapt;
import com.example.tryretrofitlogin.adapter.RVadapter;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.getallimage.GetAllImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageRetrieveTry extends AppCompatActivity {

    private RecyclerView imgRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_retrieve_try);

        imgRecView = (RecyclerView) findViewById(R.id.RV_hori);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false);
        imgRecView.setLayoutManager(layoutManager);
        imgRecView.setHasFixedSize(true);
        getAllImage();
    }

    public void getAllImage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetAllImageResponse> call = service.getimage();

        call.enqueue(new Callback<GetAllImageResponse>() {
            @Override
            public void onResponse(Call<GetAllImageResponse> call, Response<GetAllImageResponse> response) {
                if (response.isSuccessful()){
                    HorizImageRVadapt horizImageRVadapt =  new HorizImageRVadapt(ImageRetrieveTry.this,response.body()
                            .getSuccess());
                    horizImageRVadapt.notifyDataSetChanged();
                    imgRecView.setAdapter(horizImageRVadapt);
                }
            }

            @Override
            public void onFailure(Call<GetAllImageResponse> call, Throwable t) {
                Toast.makeText(ImageRetrieveTry.this, "gkkeget", Toast.LENGTH_SHORT).show();
            }
        });
    }
}