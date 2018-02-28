package com.goubaa.harry.nightplus.Library;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

/**
 * Created by harry on 2018/2/28.
 */

public class CustomPicasso {
  private static String LOG_TAG = CustomPicasso.class.getSimpleName();
  private static boolean hasCustomPicassoSingletonInstanceSet;

  public static Picasso with(Context context) {

    if (hasCustomPicassoSingletonInstanceSet) return Picasso.with(context);

    try {
      Picasso.setSingletonInstance(null);
    } catch (IllegalStateException e) {
      return Picasso.with(context);
    }

    Picasso picasso = new Picasso.Builder(context).
      downloader(new OkHttp3Downloader(context)).build();

    Picasso.setSingletonInstance(picasso);
    hasCustomPicassoSingletonInstanceSet = true;

    return picasso;
  }

  public static Picasso getNewInstance(Context context) {
    return new Picasso.Builder(context).
      downloader(new OkHttp3Downloader(context)).build();
  }
}
