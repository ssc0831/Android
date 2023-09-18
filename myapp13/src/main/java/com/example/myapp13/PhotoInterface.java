package com.example.myapp13;

import com.example.myapp13.post.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoInterface {
    //doGetPhotos()를 부르면 @get("photos/")가 실행
    @GET("photos/")
    Call<List<Photo>> doGetPhotos();
    @GET("posts/")
    Call<List<Post>> doGetPost();
}
