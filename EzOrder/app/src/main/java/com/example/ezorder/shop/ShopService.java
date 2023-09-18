package com.example.ezorder.shop;

import com.example.ezorder.order.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShopService {
    @GET("/shop/list")
    Call<List<Shop>> findAll();
    @GET("shop/view/{shopId}")
    Call<Shop> findByShopId(@Path("shopId") long shopId);
}
