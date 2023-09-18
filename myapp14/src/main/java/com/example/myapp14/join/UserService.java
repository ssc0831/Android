package com.example.myapp14.join;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @PUT("/join")
    Call<User> join(@Body User user);
    @POST("/login")
    Call<Integer> login(@Body User user);
}
