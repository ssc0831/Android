package com.example.myapp14;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneClient {
    private static PhoneClient instance;
    private PhoneService phoneService;
    public PhoneClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.20:8899/")
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
