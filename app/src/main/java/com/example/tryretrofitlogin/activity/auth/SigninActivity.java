package com.example.tryretrofitlogin.activity.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.activity.AboutoneActivity;
import com.example.tryretrofitlogin.activity.HomeActivity;
import com.example.tryretrofitlogin.activity.MainActivity;
import com.example.tryretrofitlogin.activity.PilihanlelActivity;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.responses.login.LoginResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn, buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        buttonSignIn = (Button) findViewById(R.id.btn_signin);
        buttonSignup = (Button) findViewById(R.id.btn_tosignup);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tosignup();
            }
        });
    }

    @Override
    public void onBackPressed() {
        SigninActivity.super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void tosignup(){
        Intent tosignup = new Intent(SigninActivity.this, AboutoneActivity.class);
        startActivity(tosignup);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
//        Toast.makeText(this,"email" + email + "password" + password ,  Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<LoginResponse> call = service.loginUser(email, password);
        Log.d("responebody",service.loginUser(email,password).toString());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                            response.body().getSuccess().getId(),
                            response.body().getSuccess().getToken(),
                            response.body().getSuccess().getName(),
                            response.body().getSuccess().getEmail());
//                    Toast.makeText(SigninActivity.this, "velue" + response.body().getSuccess().getName()
//                            + response.body().getSuccess().getId() + "sdsd", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
//                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
