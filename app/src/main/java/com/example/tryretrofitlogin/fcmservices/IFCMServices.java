package com.example.tryretrofitlogin.fcmservices;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMServices {
    @Headers({
            "Authorization: key=AAAAODzPflE:APA91bGH8ydG0f2DiRTJLCUhxDE4-y2VOKLIV_72puAvjNSBq3TvhBzC3mLy9F5orpTCAgjsCg7yvDtiuQBzqPV3ZJbB2RErnw8ayM9j-iI7ttEI04jPeLPJ8TBOzbgo7EqxiF4DZzTD",
            "Content-Type: application/json"
    })

    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body Sender body);
}
