package com.example.myapp16;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService {
    //추가
    @PUT("/insert")
    Call<Person> save(@Body Person person);
    //전체보기
    @GET("/list")
    Call<List<Person>> findAll();
    //수정
    @PUT("/update/{id}")
    Call<Person> update(@Path("id")long id, @Body Person person);
    //삭제
    @DELETE("/delete/{id}")
    Call<Void> deleteById(@Path("id") long id);
}
