package com.example.ezorder.fcm;

import android.os.Build;

import com.example.ezorder.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FcmClient {
    private static FcmClient instance;
    private MyFcmPostService myFcmPostService;

    public static FcmClient getInstance(){
        if(instance==null){
            instance=new FcmClient();
        }
        return instance;
    }
    public FcmClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myFcmPostService = retrofit.create(MyFcmPostService.class);
    }

    public MyFcmPostService getMyFcmPostService() {
        return myFcmPostService;
    }
}
