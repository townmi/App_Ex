package com.goubaa.harry.nightplus.Base;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goubaa.harry.nightplus.Adapter.CityTypeAdapter;
import com.goubaa.harry.nightplus.Adapter.ExploreBannerAdapter;
import com.goubaa.harry.nightplus.Adapter.ExplorePostsAdapter;
import com.goubaa.harry.nightplus.Adapter.PostTypeAdapter;
import com.goubaa.harry.nightplus.Adapter.UserTypeAdapter;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.ExproleBanner;
import com.goubaa.harry.nightplus.Models.ExprolePosts;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.User;
import com.goubaa.harry.nightplus.SessionApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitFactory
 *
 * @author HarryTang
 */
public class RetrofitFactory {
  private static final String VENUES_CORE_BASE_URL = "http://venuescore.staging.ye-dian.com/";
  private static final String COMMUNITY_CORE_BASE_URL = "http://community.staging.ye-dian.com/";
  private static final String CMS_CORE_BASE_URL = "http://cmscore.staging.ye-dian.com/";
  private static final String USER_BASE_URL = "http://user.staging.ye-dian.com/";
  private static final String SEARCH_BASE_URL = "http://search.staging.ye-dian.com/";
  private static final long TIMEOUT = 30;

  private static OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request.Builder builder = chain.request().newBuilder();
      builder.addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTgxNDY0OTcsImlzcyI6ImUzZWQwOWQwLWVhMDgtMTFlNy1hYzE0LTZmMDVmZDhlNTU1OSIsImlhdCI6MTUxNzU0MTY5N30.1UBq89"
        + "-AyxltTs0aovdL-2JHF1Vv0QzRgQMDDzV-w8w");
      return chain.proceed(builder.build());
    }
  }).addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
    @Override
    public void log(String message) {
      LogUtil.debug(message);
    }
  }).setLevel(HttpLoggingInterceptor.Level.BASIC)).connectTimeout(TIMEOUT, TimeUnit.SECONDS).cache(new Cache(new File(SessionApplication.mContext.getExternalCacheDir(), "cache"), 10 * 1024 * 1024))
    .readTimeout(TIMEOUT, TimeUnit.SECONDS).build();

  // venuesCore service
  private static RetrofitService venuesCoreRetrofitService = new Retrofit.Builder().baseUrl(VENUES_CORE_BASE_URL).addConverterFactory(GsonConverterFactory.create(buildCityGson())).addCallAdapterFactory
    (RxJava2CallAdapterFactory.create()).client(httpClient).build().create(RetrofitService.class);

  // community core service
  private static RetrofitService communityCoreRetrofitService = new Retrofit.Builder().baseUrl(COMMUNITY_CORE_BASE_URL).addConverterFactory(GsonConverterFactory.create(buildPostGson())).addCallAdapterFactory
    (RxJava2CallAdapterFactory.create()).client(httpClient).build().create(RetrofitService.class);

  // user model
  private static RetrofitService userRetrofitService = new Retrofit.Builder().baseUrl(USER_BASE_URL).addConverterFactory(GsonConverterFactory.create(buildUserGson())).addCallAdapterFactory
    (RxJava2CallAdapterFactory.create()).client(httpClient).build().create(RetrofitService.class);

  // cms model
  private static RetrofitService cmsRetorfitService = new Retrofit.Builder().baseUrl(CMS_CORE_BASE_URL).addConverterFactory(GsonConverterFactory.create(buildCmsGson())).addCallAdapterFactory
    (RxJava2CallAdapterFactory.create()).client(httpClient).build().create(RetrofitService.class);

  // home posts model
  private static RetrofitService explorePostsRetorfitService = new Retrofit.Builder().baseUrl(SEARCH_BASE_URL).addConverterFactory(GsonConverterFactory.create(buildExplorePostsGson())).addCallAdapterFactory
    (RxJava2CallAdapterFactory.create()).client(httpClient).build().create(RetrofitService.class);


  public static RetrofitService getVenuesCoreRetrofitService() {
    return venuesCoreRetrofitService;
  }

  public static RetrofitService getCommunityCoreRetrofitService() {
    return communityCoreRetrofitService;
  }

  public static RetrofitService getUserRetrofitService() {
    return userRetrofitService;
  }

  public static RetrofitService getCmsRetorfitService() {
    return cmsRetorfitService;
  }

  public static RetrofitService getExplorePostsRetorfitService() {
    return explorePostsRetorfitService;
  }

  @NonNull
  private static Gson buildCityGson() {
    return new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(City.class, new CityTypeAdapter()).create();
  }

  @NonNull
  private static Gson buildPostGson() {
    return new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(Post.class, new PostTypeAdapter()).create();
  }

  @NonNull
  private static Gson buildUserGson() {
    return new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(User.class, new UserTypeAdapter()).create();
  }

  @NonNull
  private static Gson buildCmsGson() {
    return new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(ExproleBanner.class, new ExploreBannerAdapter()).create();
  }

  @NonNull
  private static Gson buildExplorePostsGson() {
    return new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(ExprolePosts.class, new ExplorePostsAdapter()).create();
  }
}
