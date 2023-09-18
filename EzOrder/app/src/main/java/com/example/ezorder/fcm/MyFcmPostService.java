package com.example.ezorder.fcm;

import com.example.ezorder.BuildConfig;
import com.example.ezorder.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyFcmPostService {
    String SERVER_KEY = BuildConfig.SERVER_KEY;
    String CONTENT_TYPE = BuildConfig.CONTENT_TYPE;

    @Headers({"Authorization: key=" + SERVER_KEY, "Content-Type:" + CONTENT_TYPE})
    @POST("fcm/send")
    Call<ResponseBody> postNotification(@Body NotificationBody notification);
}
