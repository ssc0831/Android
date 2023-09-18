package com.example.ezorder.order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {
    //주문 입력
    @PUT("/order/insert")
    Call<Void> save(@Body OrderInfo orderInfo);
    //주문리스트 (findby memberid)
    @GET("/order/list/{memberName}")
    Call<List<OrderInfo>> OrderInfoListByMemberName(@Path("memberName") String memberName);

}
