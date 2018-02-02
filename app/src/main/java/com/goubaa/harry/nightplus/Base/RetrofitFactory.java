package com.goubaa.harry.nightplus.Base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goubaa.harry.nightplus.Adapter.CityTypeAdapter;
import com.goubaa.harry.nightplus.Adapter.PostTypeAdapter;
import com.goubaa.harry.nightplus.Adapter.UserTypeAdapter;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.User;

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
  private static final String USER_BASE_URL = "http://user.staging.ye-dian.com/";
  private static final long TIMEOUT = 30;

  private static OkHttpClient httpClient = new OkHttpClient.Builder()
    .addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTgxNDY0OTcsImlzcyI6ImUzZWQwOWQwLWVhMDgtMTFlNy1hYzE0LTZmMDVmZDhlNTU1OSIsImlhdCI6MTUxNzU0MTY5N30.1UBq89-AyxltTs0aovdL-2JHF1Vv0QzRgQMDDzV-w8w");
        return chain.proceed(builder.build());
      }
    })
    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {
        Log.i("HTTP", message);
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

  // user model
  private static RetrofitService userRetrofitService = new Retrofit.Builder()
    .baseUrl(USER_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(buildUserGson()))
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

  public static RetrofitService getUserRetrofitService() {
    return userRetrofitService;
  }

  @NonNull
  private static Gson buildGson() {
    return new GsonBuilder()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .registerTypeAdapter(City.class, new CityTypeAdapter())
      .create();
  }

  @NonNull
  private static Gson buildPostGson() {
    return new GsonBuilder()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .registerTypeAdapter(Post.class, new PostTypeAdapter())
      .create();
  }

  @NonNull
  private static Gson buildUserGson() {
    return new GsonBuilder()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .registerTypeAdapter(User.class, new UserTypeAdapter())
      .create();
  }
}
