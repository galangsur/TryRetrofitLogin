package com.example.tryretrofitlogin.api;

import com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse.GetLelangbyHewanResponse;
import com.example.tryretrofitlogin.postresponse.addReqlelangtoadmin.AddReqlelangtoadminResponse;
import com.example.tryretrofitlogin.postresponse.addhasillelang.AddHasilLelangResponse;
import com.example.tryretrofitlogin.postresponse.addlelang.AddLelangResponse;
import com.example.tryretrofitlogin.postresponse.addlelangberlangsung.AddlelbrlangsungResponse;
import com.example.tryretrofitlogin.postresponse.addlelpesertamanager.AddlelpsrtmanagerResponse;
import com.example.tryretrofitlogin.postresponse.addlprdktrans.AddprdktransResponse;
import com.example.tryretrofitlogin.postresponse.addphoto.PostphotoResponse;
import com.example.tryretrofitlogin.postresponse.addproduk.AddprodukResponse;
import com.example.tryretrofitlogin.postresponse.addratingnreview.Addratingnreview;
import com.example.tryretrofitlogin.postresponse.addreqtopup.AddtopupreqResponse;
import com.example.tryretrofitlogin.postresponse.addrequestlelang.AddreqlelangResponse;
import com.example.tryretrofitlogin.postresponse.addrequestsignuptoadmin.AddreqsignuptoadminResponse;
import com.example.tryretrofitlogin.postresponse.addtranscomp.ResponseAddtranscomp;
import com.example.tryretrofitlogin.postresponse.addwallet.AddWalletResponse;
import com.example.tryretrofitlogin.postresponse.createleltrans.LeltransAddResponse;
import com.example.tryretrofitlogin.putresponse.kurangisaldo.KurangisaldoResponse;
import com.example.tryretrofitlogin.putresponse.putgchatid.UpdategchatResponse;
import com.example.tryretrofitlogin.putresponse.putleltransstat.UpdateleltransstatResponse;
import com.example.tryretrofitlogin.putresponse.putprdktransstat.UpdateprdktransstatResponse;
import com.example.tryretrofitlogin.responses.deletereqlel.DeletereqlelResponse;
import com.example.tryretrofitlogin.responses.detlelbrjalanbyid.DetlelbrjalanbyidResponse;
import com.example.tryretrofitlogin.responses.getalasancomplaint.GetAlasanComplaintResponse;
import com.example.tryretrofitlogin.responses.getalldatauserbyid.GetalldatauserbyidResponse;
import com.example.tryretrofitlogin.responses.getallimage.GetAllImageResponse;
import com.example.tryretrofitlogin.responses.getallproduk.GetallprodukResponse;
import com.example.tryretrofitlogin.responses.gethewanbyid.GethewanbyidResponse;
import com.example.tryretrofitlogin.responses.getimgbyparent.GetimgbyparentResponse;
import com.example.tryretrofitlogin.responses.getlelang.GetlelangResponse;
import com.example.tryretrofitlogin.responses.getlelangbyid.GetlelangbyidResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbygc.GetlelbrjalanbygcResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyid.GetlelbrjalanbyidResponse;
import com.example.tryretrofitlogin.responses.getlelbrjalanbyuser.GetlelbrjalanbyuserResponse;
import com.example.tryretrofitlogin.responses.getleldiikutipeserta.GetleldiikutipesertaResponse;
import com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan.Getlelpesertabyidlelberjalan;
import com.example.tryretrofitlogin.responses.getleltransbyid.GetleltransbyidResponse;
import com.example.tryretrofitlogin.responses.getleltransbyimageparent.Getleltransbyimgparent;
import com.example.tryretrofitlogin.responses.getleltransbypllg.GetleltransbypllgResponse;
import com.example.tryretrofitlogin.responses.getleltransbypsrt.GetleltransbypsrtResponse;
import com.example.tryretrofitlogin.responses.getlistleltransbypeserta.GetListLelbypesertaResponse;
import com.example.tryretrofitlogin.responses.getprdktransbyid.GetprdktransbyidResponse;
import com.example.tryretrofitlogin.responses.getprdktransbypmbl.GetprdktransbypmblResponse;
import com.example.tryretrofitlogin.responses.getprdktransbypnjl.GetprdktransbypnjlResponse;
import com.example.tryretrofitlogin.responses.getratingreviewbytoken.Getreviewratingbytoken;
import com.example.tryretrofitlogin.responses.getreqlel.GetReqlelResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyid.GetreqlelbyidResponse;
import com.example.tryretrofitlogin.responses.getreqlelbysender.GetreqlelbysenderResponse;
import com.example.tryretrofitlogin.responses.getreqlelbyuser.GetreqlelbyuserResponse;
import com.example.tryretrofitlogin.responses.getsolusicomplaint.GetSolusiComplaintResponse;
import com.example.tryretrofitlogin.responses.getusernamebyid.GetusernamebyidResponse;
import com.example.tryretrofitlogin.responses.getuserbyname.GetuseridResponse;
import com.example.tryretrofitlogin.responses.signup.AuthResponse;
import com.example.tryretrofitlogin.responses.gethewan.HewanResponse;
import com.example.tryretrofitlogin.responses.getwallet.GetWalletInfoResponse;
import com.example.tryretrofitlogin.responses.login.LoginResponse;
import com.example.tryretrofitlogin.responses.topupwallet.TopupResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @FormUrlEncoded
    @POST("register")
    Call<AuthResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("tlp") String tlp,
            @Field("password") String password,
            @Field("c_password") String c_password
    );

    @FormUrlEncoded
    @POST("addsignupreq")
    Call<AddreqsignuptoadminResponse> requestSignuptoAdmin(
            @Field("name") String name,
            @Field("email") String email,
            @Field("tlp") String tlp,
            @Field("password") String password,
            @Field("nikktp") String nikktp,
            @Field("ratingnreview_token") String ratingnreview_token
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
            @Field("img_lelang") String img_lelang,
            @Field("imgparent_token") String imgparent_token,
            @Field("sertifparent_token") String sertifparent_token,
            @Field("req_waktuperbid") String req_waktuperbid,
            @Field("req_nominalperbid") String req_nominalperbid
    );

//    @Multipart
//    @POST("imagepost")
//    Call<PostpotoResponse> pos(
//            @Part ("imgparent") RequestBody imgparent,
//            @Part MultipartBody.Part map
//    );

    @Multipart
    @POST("addlelangreq")
    Call<AddReqlelangtoadminResponse> requestLelangtoAdmin(
            @Part ("user_id") RequestBody user_id,
            @Part ("hewan_id") RequestBody hewan_id,
            @Part ("harga") RequestBody harga,
            @Part ("comment") RequestBody comment,
            @Part MultipartBody.Part img_lelang,
            @Part ("umur_hewan") RequestBody umur_hewan,
            @Part ("jeniskelamin_hewan") RequestBody jeniskelamin_hewan,
            @Part ("warna_hewan") RequestBody warna_hewan,
            @Part ("pesertaonline_token") RequestBody pesertaonline_token,
            @Part ("imgparent_token") RequestBody imgparent_token,
            @Part ("sertifparent_token") RequestBody sertifparent_token,
            @Part ("req_waktuperbid") RequestBody req_waktuperbid,
            @Part ("req_nominalperbid") RequestBody req_nominalperbid
    );

//    @FormUrlEncoded
//    @POST("addlelangreq")
//    Call<Addreqleltoadmin> req(
//            @Field("user_id") String user_id,
//            @Field("hewan_id") int hewan_id,
//            @Field("harga") String harga,
//            @Field("comment") String comment,
//            @Field("img_lelang") String img_lelang,
//            @Field("req_waktuperbid") String req_waktuperbid,
//            @Field("req_nominalperbid") String req_nominalperbid
//    );

    @FormUrlEncoded
    @POST("lelbrjalan")
    Call<AddlelbrlangsungResponse> lelbrlngsung(
            @Field("user_id") String user_id,
            @Field("hewan_id") int hewan_id,
            @Field("comment") String comment,
            @Field("harga") String harga,
            @Field("gchat_id") String gchat_id,
            @Field("pesertaonline_token") String pesertaonline_token
    );

    @FormUrlEncoded
    @POST("produk")
    Call<AddprodukResponse> createproduk(
            @Field("user_id") String user_id,
            @Field("hewan_id") int hewan_id,
            @Field("tentang_produk") String tentang_produk,
            @Field("jumlah_produk") String jumlah_produk,
            @Field("harga_produk") String harga_produk
    );

    @FormUrlEncoded
    @POST("wallet")
    Call<AddWalletResponse> createWallet(
            @Field("user_id") String user_id,
            @Field("saldo") String saldo
            );

    @FormUrlEncoded
    @POST("addtopupreq")
    Call<AddtopupreqResponse> createTopupreq(
            @Field("user_id") String user_id,
            @Field("wallet_id") String wallet_id,
            @Field("nama_user") String nama_user,
            @Field("reqnominal") String reqnominal
    );

    @FormUrlEncoded
    @POST("leltrans")
    Call<LeltransAddResponse> addLeltransak(
            @Field("hasillelang_token") String hasillelang_token,
            @Field("statushasil_id") String statushasil_id,
            @Field("statushasil") String statushasil,
            @Field("peserta_id") String peserta_id,
            @Field("pelelang_id") String pelelang_id,
            @Field("hewan") String hewan,
            @Field("harga_lelang") String harga_lelang,
            @Field("nilai_akhir") String nilai_akhir
    );

    @FormUrlEncoded
    @POST("prdktrans")
    Call<AddprdktransResponse> addprdktrans(
            @Field("produk_id") String produk_id,
            @Field("prdktrans_token") String prdktrans_token,
            @Field("statushasil_id") String statushasil_id,
            @Field("statushasil") String statushasil,
            @Field("pembeli_id") String pembeli_id,
            @Field("penjual_id") String penjual_id,
            @Field("hewan") String hewan,
            @Field("jumlah") String jumlah,
            @Field("harga_produk") String harga_produk
    );

    @FormUrlEncoded
    @POST("reqlelang")
    Call<AddreqlelangResponse> reqLelang(
            @Field("pengirim_id") String pengirimId,
            @Field("user_id") String userId,
            @Field("lelang_id") String lelangId
    );

    @FormUrlEncoded
    @POST("addreviewnrating")
    Call<Addratingnreview> addratingnreview(
            @Field("nama_reviewer") String nama_reviewer,
            @Field("ratingnreview_token") String ratingnreview_token,
            @Field("rating") float rating,
            @Field("review") String review
    );

    @FormUrlEncoded
    @POST("hasillelang")
    Call<AddHasilLelangResponse> addhasilLelang(
            @Field("hasillelangs_token") String hasillelangs_token,
            @Field("statushasil_id") String statushasil_id,
            @Field("statustrans") String statustrans,
            @Field("peserta_id") String peserta_id,
            @Field("pelelang_id") String pelelang_id,
            @Field("lelbrjalan_id") String lelbrjalan_id,
            @Field("hewan_id") String hewan_id,
            @Field("harga_lelang") String harga_lelang,
            @Field("nilai_akhir") String nilai_akhir
    );

    @FormUrlEncoded
    @POST("accreqlel")
    Call<AddlelpsrtmanagerResponse> accPeserta(
            @Field("peserta_id") String peserta_id,
            @Field("lelbrjalan_id") String lelbrjalan_id,
            @Field("hewan") String hewan
    );

    @FormUrlEncoded
    @POST("addcompreq")
    Call<ResponseAddtranscomp> uploadTranscomp(
            @Field("transaksi_id") String transaksi_id,
            @Field("idpengaju_complaint") String idpengaju_complaint,
            @Field("namapengaju_complaint") String namapengaju_complaint,
            @Field("alasan_complaint") int alasan_complaint,
            @Field("solusi_complaint") int solusi_complaint,
            @Field("img_complaint") String img_complaint
    );

    //jika mengirim file saja pakai cara ini
//    @Multipart
//    @POST("imagepost")
//    Call<PostphotoResponse> postPhoto(
//            @Part MultipartBody.Part map
//    );

    //jika mengirim file berisi string atau int, pakai cara berikut
    @Multipart
    @POST("imagepost")
    Call<PostphotoResponse> postPhoto(
            @Part ("imgparent") RequestBody imgparent,
            @Part MultipartBody.Part map
    );
    // walaupun tipe string atau int, tetap pakai RequestBody



//---------------------PUT RESPONSE------------------------------------------------//
    @FormUrlEncoded
    @PUT("tambahsaldo/{id}")
    Call<TopupResponse> saldotambah(
            @Path("id") String id,
            @Field("saldo") String saldo
    );

    @FormUrlEncoded
    @PUT("kurangisaldo/{id}")
    Call<KurangisaldoResponse> saldokurang(
            @Path("id") String id,
            @Field("saldo") String saldo
    );

    @FormUrlEncoded
    @PUT("ubahstatustrans/{id}")
    Call<UpdateleltransstatResponse> ubahstattrans(
            @Path("id") String id,
            @Field("hasillelang_token") String hasillelang_token,
            @Field("statushasil_id") String statushasil_id,
            @Field("statushasil") String statushasil

    );

    @FormUrlEncoded
    @PUT("ubahstattransprdk/{id}")
    Call<UpdateprdktransstatResponse> ubahstattransprdk(
            @Path("id") String id,
            @Field("statushasil_id") String statushasil_id,
            @Field("statushasil") String statushasil
    );

    @FormUrlEncoded
    @PUT("mulailelbrjalan/{id}")
    Call<UpdategchatResponse> updatemulai(
            @Path("id") String id,
            @Field("gchat_id") String gchatId
    );


//--------------------------GET RESPONSE----------------------------------------------------------//
    @GET("getwallet/{user_id}")
    Call<GetWalletInfoResponse> getinfosaldo(
            @Path("user_id") String user_id,
            @Query("saldo") String saldo
    );

    @GET("getlelbrjalanbygc/{gchat_id}")
    Call<GetlelbrjalanbygcResponse> getlelbrjalanbygc(
            @Path("gchat_id") String gchat_id,
            @Query("user_id") String user_id,
            @Query("hewan_id") String hewan_id,
            @Query("harga") String harga,
            @Query("comment") String comment,
            @Query("req_waktuperbid") int waktuperbid,
            @Query("req_nominalperbid") int nominalperbid
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

    @GET("getallpesertabylelid/{lelbrjalan_id}")
    Call<Getlelpesertabyidlelberjalan> getLelpesertabyidlelberjalan(
            @Path("lelbrjalan_id") String lelbrjalan_id
    );

    @GET("getreviewnratingbytoken/{ratingnreview_token}")
    Call<Getreviewratingbytoken> getreviewratingbytoken(
            @Path("ratingnreview_token") String ratingnreview_token
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
    Call<GetLelangbyHewanResponse> getLelbyhewan(
            @Path("id") String id
    );

    @GET("getlelbrjalanbyuser/{user_id}")
    Call<GetlelbrjalanbyuserResponse> getlelbrjlanbyuser(
            @Path("user_id") String id
    );




    //translel/////////////////////////////////////////////////
    @GET("getleltransbypsrt/{id}")
    Call<GetleltransbypsrtResponse> leltransbypsrt(
            @Path("id") String id
    );
    @GET("getleltransbypllg/{id}")
    Call<GetleltransbypllgResponse> leltransbypllg(
            @Path("id") String id
    );
    @GET("getleltransbyid/{id}")
    Call<GetleltransbyidResponse> leltransbyid(
            @Path("id") String id
    );

    @GET("getleltransbyleltoken/{hasillelang_token}")
    Call<Getleltransbyimgparent> leltransbyleltoken(
            @Path("hasillelang_token") String hasillelang_token,
            @Query("id") String id
//            @Query("statushasil_id") String statushasil_id,
//            @Query("statushasil") String statushasil,
//            @Query("peserta_id") String peserta_id,
//            @Query("pelelang_id") String pelelang_id,
//            @Query("hewan") String hewan,
//            @Query("harga_lelang") int harga_lelang,
//            @Query("nilai_akhir") int nilai_akhir
    );

    //transprdk/////////////////////////////////////////////////
    @GET("getprdktransbypnjl/{id}")
    Call<GetprdktransbypnjlResponse> prdktransbypnjl(
            @Path("id") String id
    );
    @GET("getprdktransbypmbl/{id}")
    Call<GetprdktransbypmblResponse> prdktransbypmbl(
            @Path("id") String id
    );
    @GET("getprdktransbyid/{id}")
    Call<GetprdktransbyidResponse> prdktransbyid(
            @Path("id") String id
    );


    @GET("gethewanbyid/{id}")
    Call<GethewanbyidResponse> gethewanjenis(
            @Path("id") String id,
            @Query("jenis") String jenis
    );

    @GET("getusernamebyid/{id}")
    Call<GetusernamebyidResponse> getUsername(
            @Path("id") String id,
            @Query("name") String name
    );

    @GET("getuserbyid/{id}")
    Call<GetalldatauserbyidResponse> getalldatauserbyid(
            @Path("id") String id
    );

    @GET("getlelangbyid/{id}")
    Call<GetlelangbyidResponse> getdetlelang(
            @Path("id") String id,
            @Query("user_id") String user_id,
            @Query("hewan_id") String hewan_id,
            @Query("comment") String comment,
            @Query("harga") String harga,
            @Query("img_lelang") String img_lelang
    );

    @GET("getlelbrjalanbyid/{id}")
    Call<GetlelbrjalanbyidResponse> getlelbrjalanbyid(
            @Path("id") String id,
            @Query("user_id") String user_id,
            @Query("hewan_id") String hewan_id,
            @Query("comment") String comment,
            @Query("harga") String harga,
            @Query("gchat_id") String gchat_id
    );

    @GET("detlelbrjalanbyid/{id}")
    Call<DetlelbrjalanbyidResponse> detletbrjalanbyid(
            @Path("id") String id,
//            @Query("user_id") String user_id,
//            @Query("hewan_id") String hewan_id,
//            @Query("comment") String comment,
//            @Query("harga") String harga,
            @Query("gchat_id") String gchat_id
    );

    @GET("getimgbyparent/{imgparent}")
    Call<GetimgbyparentResponse> getimgbyparent(
            @Path("imgparent") String id
    );

    @GET("gethlelangbypeserta/{peserta_id}")
    Call<GetListLelbypesertaResponse> getlistleltrans(
            @Path("peserta_id") String id
    );

    @GET("getlelpesertabypeserta/{peserta_id}")
    Call<GetleldiikutipesertaResponse> psrtmanagerbyuser(
            @Path("peserta_id") String id
    );


    @GET("hewan")
    Call<HewanResponse> gethewan(
    );

    @GET("alasancomplaint")
    Call<GetAlasanComplaintResponse> getallalasan(
    );

    @GET("solusicomplaint")
    Call<GetSolusiComplaintResponse> getallsolusi(
    );

    @GET("alllelang")
    Call<GetlelangResponse> getlelang(
    );

    @GET("getallimage")
    Call<GetAllImageResponse> getimage(
    );

    @GET("allproduk")
    Call<GetallprodukResponse> getproduk(
    );

    @GET("allreqlel")
    Call<GetReqlelResponse> getreqlel(
    );




}
