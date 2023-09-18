package com.example.cafemanager.fcm;

import com.example.cafemanager.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FcmClient {
    private static FcmClient instance;
    private FcmPostService fcmPostService;

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

        fcmPostService = retrofit.create(FcmPostService.class);
    }
    //getter


    public FcmPostService getFcmPostService() {
        return fcmPostService;
    }
}
