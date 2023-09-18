package com.example.ezorder.order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuService {
    @GET("/menu/list/{shopid}")
    Call<List<Menu>> findByShop(@Path("shopid") long shopid);
}
