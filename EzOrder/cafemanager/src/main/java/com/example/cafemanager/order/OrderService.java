package com.example.cafemanager.order;

import com.example.cafemanager.shop.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderService {
    //가게로 주문 리스트
    @GET("/order/listall")
    Call<List<OrderInfo>> findAllByShop(@Query("shopId") long shopId);
    @GET("/order/updatestatus")
    Call<Void> updateOrderStatus(@Query("orderId") long orderId);
}
