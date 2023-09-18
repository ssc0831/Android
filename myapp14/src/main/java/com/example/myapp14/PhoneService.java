package com.example.myapp14;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PhoneService {
    //전체보기
    @GET("/list")
    Call<List<Phone>> findAll();
    //추가
    @POST("/insert")
    Call<Phone> save(@Body Phone phone);
    //수정
    //삭제
    @DELETE("/delete/{id}")
    Call<Void> deleteById(@Path("id") long id);

    @PUT("/update/{id}")
    Call<Phone> updatebyId(@Path("id") long id,@Body Phone phone);

}
