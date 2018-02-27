package com.goubaa.harry.nightplus.Base;

import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.GraphqlRows;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.User;
import com.goubaa.harry.nightplus.Models.ExproleBanner;


import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
  @GET("public/cities/")
  Observable<BaseEntity<City>> getCities();

  @GET("community/post/{id}")
  Observable<BaseEntity<Post>> getPosts(@Path("id") String id);

  @GET("internal/userInfo?_type=User")
  Observable<BaseEntityObject<User>> getUserInfo();

  @GET("public/graphql")
  Observable<BaseEntityObject<GraphqlRows<ExproleBanner>>> getBanners(@Query("query") String query);
}