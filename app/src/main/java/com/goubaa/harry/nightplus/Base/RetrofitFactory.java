package com.goubaa.harry.nightplus.Base;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goubaa.harry.nightplus.Adapter.CityTypeAdapter;
import com.goubaa.harry.nightplus.Adapter.PostTypeAdapter;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.Post;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harry on 2018/1/25.
 */

public class RetrofitFactory {
  private static final String BASE_URL = "http://venuescore.staging.ye-dian.com/";
  private static final String VENUES_CORE_BASE_URL = "http://venuescore.staging.ye-dian.com/";
  private static final String COMMUNITY_CORE_BASE_URL = "http://community.staging.ye-dian.com/";
  private static final long TIMEOUT = 30;

  private static OkHttpClient httpClient = new OkHttpClient.Builder()
    .addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTc0NTI5MDYsImlzcyI6ImUzZWQwOWQwLWVhMDgtMTFlNy1hYzE0LTZmMDVmZDhlNTU1OSIsImlhdCI6MTUxNjg0ODEwNn0.c9sQWEWFYLmSVkLVIJwfb4evKWdrT4jTb8KrM1T3Peg");
        return chain.proceed(builder.build());
      }
    })
    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {

      }
    }).setLevel(HttpLoggingInterceptor.Level.BASIC))
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .build();

  // venuesCore service
  private static RetrofitService retrofitService = new Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(buildGson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(httpClient)
    .build()
    .create(RetrofitService.class);

  // venuesCore service
  private static RetrofitService venuesCoreRetrofitService = new Retrofit.Builder()
    .baseUrl(VENUES_CORE_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(buildPostGson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(httpClient)
    .build()
    .create(RetrofitService.class);

  // community core service
  private static RetrofitService communityCoreRetrofitService = new Retrofit.Builder()
    .baseUrl(COMMUNITY_CORE_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(buildPostGson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(httpClient)
    .build()
    .create(RetrofitService.class);

  public static RetrofitService getInstance() {
    return retrofitService;
  }

  public static RetrofitService getVenuesCoreRetrofitService() {
    return venuesCoreRetrofitService;
  }

  public static RetrofitService getCommunityCoreRetrofitService() {
    return communityCoreRetrofitService;
  }

  private static Gson buildGson() {
    return new GsonBuilder()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .registerTypeAdapter(City.class, new CityTypeAdapter())
      .create();
  }

  private static Gson buildPostGson() {
    return new GsonBuilder()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .registerTypeAdapter(Post.class, new PostTypeAdapter())
      .create();
  }
}
