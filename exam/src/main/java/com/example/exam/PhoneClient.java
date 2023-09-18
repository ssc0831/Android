package com.example.exam;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneClient {
    private static PhoneClient instance;
    private final PhoneService phoneService;
    public PhoneClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.25:8899/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        phoneService = retrofit.create(PhoneService.class);
    }
    public static PhoneClient getInstance(){
        if(instance==null){
            instance=new PhoneClient();
        }
        return instance;
    }
    public PhoneService getPhoneService(){
        return phoneService;
    }
}
