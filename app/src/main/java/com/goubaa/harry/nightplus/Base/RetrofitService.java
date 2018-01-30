package com.goubaa.harry.nightplus.Base;

import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.Post;


import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
  @GET("public/cities/")
  Observable<BaseEntity<City>> getCities();

  @GET("community/post/{id}")
  Observable<BaseEntity<Post>> getPosts(
    @Path("id") String id
  );
}