package com.example.cafemanager.fcm;

import com.example.cafemanager.BuildConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FcmPostService {
    String SERVER_KEY = BuildConfig.SERVER_KEY;
    String CONTENT_TYPE = BuildConfig.CONTENT_TYPE;

    @Headers({"Authorization: key=" + SERVER_KEY, "Content-Type:" + CONTENT_TYPE})
    @POST("fcm/send")
    Call<ResponseBody> postNotification(@Body NotificationBody notification);
}
