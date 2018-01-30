package com.goubaa.harry.nightplus.Library;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by harry on 2017/12/8.
 */

public class CustomToast {

  private static Toast toast;

  /**
   * 短时间显示
   *
   * @param context
   * @param message
   */
  public static void showShort(Context context, CharSequence message) {
    if (null == toast) {
      toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 短时间显示
   *
   * @param context
   * @param message
   */
  public static void showShort(Context context, int message) {
    if (null == toast) {
      toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 长时间显示
   *
   * @param context
   * @param message
   */
  public static void showLong(Context context, CharSequence message) {
    if (null == toast) {
      toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 长时间显示
   *
   * @param context
   * @param message
   */
  public static void showLong(Context context, int message) {
    if (null == toast) {
      toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 自定义显示时间
   *
   * @param context
   * @param message
   * @param duration
   */
  public static void show(Context context, CharSequence message, int duration) {
    if (null == toast) {
      toast = Toast.makeText(context, message, duration);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 自定义显示时间
   *
   * @param context
   * @param message
   * @param duration
   */
  public static void show(Context context, int message, int duration) {
    if (null == toast) {
      toast = Toast.makeText(context, message, duration);
    } else {
      toast.setText(message);
    }
    toast.show();
  }

  /**
   * 隐藏toast
   */
  public static void hideToast() {
    if (null != toast) {
      toast.cancel();
    }
  }

}
