package com.example.ezorder.member;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MemberService {
    @GET("/member/insertMember/")
    Call<Void> saveMember(@Query("memberName") String memberName);

    @POST("/member/updateToken/")
    Call<Void> saveToken(@Body Member member);
}
