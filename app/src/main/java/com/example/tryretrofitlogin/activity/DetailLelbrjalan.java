package com.example.tryretrofitlogin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.responses.detlelbrjalanbyid.DetlelbrjalanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyid.GetlelbrjalanbyidResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailLelbrjalan extends AppCompatActivity {
    private String lelbrjalanid;
    private String lelbrjalan, lelbrjalancomment, lelbrjalanharga, lelbrjalanhewan, lelbrjalanpelelang, lelbrjalangctoken;
    private TextView tmpgctoken,tmpidlelbrjalan;
    private Button btntogchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelbrjalan);

            tmpgctoken = (TextView)findViewById(R.id.gctoken);
            tmpidlelbrjalan = (TextView)findViewById(R.id.tmp_idlelbrjalan);
            btntogchat = (Button)findViewById(R.id.btnto_gchat);

        Intent lelintent = getIntent();
        lelbrjalanid = lelintent.getStringExtra("idlelbrjalan");
        tmpidlelbrjalan.setText(lelbrjalanid);

        getLelbrjalan();

        btntogchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailLelbrjalan.this, "gc" + tmpgctoken, Toast.LENGTH_SHORT).show();
                Intent lelaspelelang = new Intent(DetailLelbrjalan.this,GroupchatActivity.class);
                lelaspelelang.putExtra("gchattoken",tmpgctoken.getText());
                DetailLelbrjalan.this.startActivity(lelaspelelang);
            }
        });
    }

    private void getLelbrjalan(){
        String lelbrjlnkey =tmpidlelbrjalan.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<DetlelbrjalanbyidResponse> call = service.detletbrjalanbyid(
                lelbrjlnkey, lelbrjalangctoken);

        call.enqueue(new Callback<DetlelbrjalanbyidResponse>() {
            @Override
            public void onResponse(Call<DetlelbrjalanbyidResponse> call, Response<DetlelbrjalanbyidResponse> response) {
                if (response.isSuccessful()){
                    tmpgctoken.setText(response.body().getSuccess().getGchatId());
                }

            }

            @Override
            public void onFailure(Call<DetlelbrjalanbyidResponse> call, Throwable t) {

            }
        });
    }
}