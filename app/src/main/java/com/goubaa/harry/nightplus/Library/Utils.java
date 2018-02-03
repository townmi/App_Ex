package com.goubaa.harry.nightplus.Library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by harry on 2018/1/24.
 */

public class Utils {
  /**
   * 判断网络是否可以使用
   */
  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isAvailable();
  }

  /**
   * GPS 定位
   */

  public static void GPS(Context context) {

  }
}
