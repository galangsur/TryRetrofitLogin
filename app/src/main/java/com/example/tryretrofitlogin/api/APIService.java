package com.example.tryretrofitlogin.api;

import com.example.tryretrofitlogin.models.Lelang;
import com.example.tryretrofitlogin.responses.addlelang.AddLelangResponse;
import com.example.tryretrofitlogin.responses.getlelang.GetlelangResponse;
import com.example.tryretrofitlogin.responses.signup.AuthResponse;
import com.example.tryretrofitlogin.responses.gethewaninfo.HewanResponse;
import com.example.tryretrofitlogin.responses.getwalletinfo.GetWalletInfoResponse;
import com.example.tryretrofitlogin.responses.login.LoginResponse;
import com.example.tryretrofitlogin.responses.wallet.TopupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("lelang")
    Call<AddLelangResponse> createLelang(
            @Field("user_id") String user_id,
            @Field("hewan_id") int hewan_id,
            @Field("harga") String harga,
            @Field("comment") String comment
    );

    @FormUrlEncoded
    @PUT("tambahsaldo/{id}")
    Call<TopupResponse> saldotambah(
            @Path("id") String id,
            @Field("saldo") String saldo
    );

    @GET("getwallet/{user_id}")
    Call<GetWalletInfoResponse> getinfosaldo(
            @Path("user_id") String user_id,
            @Query("saldo") String saldo
    );

    @GET("hewan")
    Call<HewanResponse> gethewan(
    );

    @GET("alllelang")
    Call<GetlelangResponse> getlelang(
    );


}
