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
import com.example.tryretrofitlogin.activity.MainActivity;
import com.example.tryretrofitlogin.activity.WalletCreateActivity;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.postresponse.addrequestsignuptoadmin.AddreqsignuptoadminResponse;
import com.example.tryretrofitlogin.responses.signup.AuthResponse;
import com.example.tryretrofitlogin.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private Button btnSignup;
    private EditText editTextName, editTextEmail, editTextPassword, editTextTlp,editTextNik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignup = (Button) findViewById(R.id.btn_signup);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextTlp = (EditText) findViewById(R.id.editTextTlp);
        editTextNik = (EditText) findViewById(R.id.editTextnikktp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTlp.getText().toString().trim().length() < 10){
                    Toast.makeText(SignupActivity.this, "nomor anda kurang", Toast.LENGTH_SHORT).show();
                } else{
                    requestSignUptoAdmin();
                }

            }
        });
    }

    private void userSignUp() {
        final String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String tlp = editTextTlp.getText().toString().trim();
        String nik = editTextNik.getText().toString().trim();


        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        User user = new User(name, email, tlp, password, password,nik);

        //defining the call
        Call<AuthResponse> call = service.createUser(
                user.getName(),
                user.getEmail(),
                user.getTlp(),
                user.getPassword(),
                user.getC_password()
        );

        //calling the api
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                intent.putExtra("username", name);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestSignUptoAdmin() {
        final String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String tlp = editTextTlp.getText().toString().trim();
        String nik = editTextNik.getText().toString().trim();


        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        User user = new User(name, email, tlp, password, password, nik);

        //defining the call
        Call<AddreqsignuptoadminResponse> call = service.requestSignuptoAdmin(
                user.getName(),
                user.getEmail(),
                user.getTlp(),
                user.getPassword(),
                user.getNikktp()
        );

        //calling the api
        call.enqueue(new Callback<AddreqsignuptoadminResponse>() {
            @Override
            public void onResponse(Call<AddreqsignuptoadminResponse> call, Response<AddreqsignuptoadminResponse> response) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                intent.putExtra("username", name);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }

            @Override
            public void onFailure(Call<AddreqsignuptoadminResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        SignupActivity.super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
