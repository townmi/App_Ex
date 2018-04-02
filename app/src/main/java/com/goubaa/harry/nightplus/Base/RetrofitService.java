package com.goubaa.harry.nightplus.Base;

import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.ExproleBanner;
import com.goubaa.harry.nightplus.Models.ExprolePosts;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.User;


import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
  @GET("public/cities/")
  Observable<BaseEntity<City>> getCities();

  @GET("community/post/{id}")
  Observable<BaseEntity<Post>> getPostInfo(@Path("id") String id);

  @GET("internal/userInfo?_type=User")
  Observable<BaseEntityObject<User>> getUserInfo();

  @GET("public/graphql")
  Observable<BaseEntityObject<ExproleBanner>> getBanners(
    @Query("query") String query
  );

  @GET("posts")
  Observable<BaseEntityObject<ExprolePosts>> getExplorePosts(
    @Query("sort") String sort,
    @Query("limit") int limit,
    @Query("offset") int offset,
    @Query("cityId") String cityId,
    @Query("isRecommend") boolean isRecommend
  );

  @GET("community/posts")
  Observable<BaseEntity<Post>> getPosts(
    @Query("sort") String sort,
    @Query("limit") int limit,
    @Query("offset") int offset,
    @Query("postTypeList") String postTypeList,
    @Query("cms") boolean cms,
    @Query("userId") String userId,
    @Query("isFollow") boolean isFollow
  );
}