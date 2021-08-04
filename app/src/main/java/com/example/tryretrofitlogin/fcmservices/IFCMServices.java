package com.example.tryretrofitlogin.fcmservices;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMServices {
    @Headers({"Authorization: key=" + Constant.FCMSERVER_KEY, "Content-Type:" + Constant.FCMCONTENT_TYPE})
    @POST("fcm/send")
    Call<ResponseMsg> postNotification(@Body PushNotification data);

    Call<ResponseMsg> sendNotfi(@Body PushNotification notification);
}
