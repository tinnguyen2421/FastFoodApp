package com.example.appfood_by_tinnguyen2421.SendNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA6z09yqM:APA91bHf6Bfa3yHtWtaDIeIPWYvzLHjGK3Lht5et146yhLiS8jc4_2mUGJ3172AaA4CKyJphhydwSZ4DQcu7pF5kZwrljHP97vGgt7U_Nes3AkEpKUBPexav_G4XCHUNcrcwCZ2teU0d"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender body);

}
