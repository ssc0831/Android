package com.example.cafemanager.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/user/login")
    Call<Integer> login(@Body User user);
}
