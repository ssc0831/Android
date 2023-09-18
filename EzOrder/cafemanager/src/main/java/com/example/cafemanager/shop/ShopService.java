package com.example.cafemanager.shop;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopService {
    @GET("shop/view/{shopId}")
    Call<Shop> findByShopId(@Path("shopId") long shopId);
    @GET("shop/updateTkn")
    Call<Void> updateTkn(@Query("token") String token,@Query("shopId")long shopId);
    @Multipart
    @POST("shop/upload")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);

    @POST("shop/insert")
    Call<Void> insertShop(@Body Shop shop);
}
