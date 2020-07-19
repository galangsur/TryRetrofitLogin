package com.example.tryretrofitlogin.api;

import com.example.tryretrofitlogin.postresponse.addlelang.AddLelangResponse;
import com.example.tryretrofitlogin.postresponse.addlelpesertamanager.AddlelpesertamanagerResponse;
import com.example.tryretrofitlogin.postresponse.addrequestlelang.AddreqlelangResponse;
import com.example.tryretrofitlogin.postresponse.addwallet.AddWalletResponse;
import com.example.tryretrofitlogin.putresponse.putgchatid.UpdategchatidResponse;
import com.example.tryretrofitlogin.responses.deletereqlel.DeletereqlelResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelang.GetlelangResponse;
import com.example.tryretrofitlogin.responses.getlelangbyhewan.GetlelangbyhewanResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getreqlel.GetReqlelResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyid.GetreqlelbyidResponse;
import com.example.tryretrofitlogin.responses.getreqlelbysender.GetreqlelbysenderResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.GetreqlelbyuserResponse;
import com.example.tryretrofitlogin.responses.getuserbyid.GetusernamebyidResponse;
import com.example.tryretrofitlogin.responses.getuserbyname.GetuseridResponse;
import com.example.tryretrofitlogin.responses.signup.AuthResponse;
import com.example.tryretrofitlogin.responses.gethewan.HewanResponse;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.example.tryretrofitlogin.responses.login.LoginResponse;
import com.example.tryretrofitlogin.responses.topupwallet.TopupResponse;

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
            @Field("comment") String comment,
            @Field("gchat_id") String gchat_id
    );



    @FormUrlEncoded
    @POST("wallet")
    Call<AddWalletResponse> createWallet(
            @Field("user_id") String user_id,
            @Field("saldo") String saldo
            );

    @FormUrlEncoded
    @POST("reqlelang")
    Call<AddreqlelangResponse> reqLelang(
            @Field("pengirim_id") String pengirimId,
            @Field("user_id") String userId,
            @Field("lelang_id") String lelangId
    );

    @FormUrlEncoded
    @POST("accreqlel")
    Call<AddlelpesertamanagerResponse> accPeserta(
            @Field("peserta_id") String peserta_id,
            @Field("lelang_id") String lelang_id
    );

    @FormUrlEncoded
    @PUT("tambahsaldo/{id}")
    Call<TopupResponse> saldotambah(
            @Path("id") String id,
            @Field("saldo") String saldo
    );

    @FormUrlEncoded
    @PUT("mulailelang/{id}")
    Call<UpdategchatidResponse> updatemulai(
            @Path("id") String id,
            @Field("gchat_id") String gchatId
    );

    @GET("getwallet/{user_id}")
    Call<GetWalletInfoResponse> getinfosaldo(
            @Path("user_id") String user_id,
            @Query("saldo") String saldo
    );

    @GET("getuserbyname/{name}")
    Call<GetuseridResponse> getUserid(
            @Path("name") String name,
            @Query("id") String id
    );

    @GET("getreqlelbyuser/{user_id}")
    Call<GetreqlelbyuserResponse> getReqlelbyuser(
            @Path("user_id") String user_id
    );

    @GET("getreqlelbypengirim/{pengirim_id}")
    Call<GetreqlelbysenderResponse> getReqlelbysender(
            @Path("pengirim_id") String pengirim_id
    );

    @GET("deletereq/{id}")
    Call<DeletereqlelResponse> deleteReqlel(
            @Path("id") String id
    );

    @GET("getreqlelbyid/{id}")
    Call<GetreqlelbyidResponse> getReqlelbyid(
            @Path("id") String id
    );

    @GET("getlelangbyhewan/{id}")
    Call<GetlelangbyhewanResponse> getLelbyhewan(
            @Path("id") String id
    );

    @GET("gethewanbyid/{id}")
    Call<GethewanbyidResponse> gethewanjenis(
            @Path("id") String id,
            @Query("jenis") String jenis
    );

    @GET("getuserbyid/{id}")
    Call<GetusernamebyidResponse> getUsername(
            @Path("id") String id,
            @Query("name") String name
    );

    @GET("getlelangbyid/{id}")
    Call<GetlelangbyidResponse> getdetlelang(
            @Path("id") String id,
            @Query("user_id") String user_id,
            @Query("hewan_id") String hewan_id,
            @Query("comment") String comment,
            @Query("harga") String harga
    );

    @GET("hewan")
    Call<HewanResponse> gethewan(
    );

    @GET("alllelang")
    Call<GetlelangResponse> getlelang(
    );

    @GET("allreqlel")
    Call<GetReqlelResponse> getreqlel(
    );




}
