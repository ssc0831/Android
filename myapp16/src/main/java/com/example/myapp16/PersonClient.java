package com.example.myapp16;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonClient {
    private static PersonClient instance;
    private PersonService personService;

    public PersonClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.20:8877/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        personService = retrofit.create(PersonService.class);
    }

    public static PersonClient getInstance(){
        if(instance==null){
            instance = new PersonClient();
        }
        return instance;
    }

    public PersonService getPersonService() {
        return personService;
    }
}
