package com.example.tryretrofitlogin.api;

import com.example.tryretrofitlogin.models.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("register")
    Call<AuthResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password
    );

    @FormUrlEncoded
    @POST("login")
    Call<AuthResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}
