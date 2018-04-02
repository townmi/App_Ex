package com.goubaa.harry.nightplus.Library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Utils
 *
 * @author harryTang
 */
public class Utils {
  private static final String marshmallowMacAddress = "02:00:00:00:00:00";
  private static final String fileAddressMac = "/sys/class/net/wlan0/address";

  /**
   * 判断网络是否可以使用
   *
   * @param context Context
   * @return boolean
   */
  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext
      ().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isAvailable();
  }

  /**
   * GPS 定位
   */

  public static void GPS(Context context) {

  }

  /**
   * getBuildInfo 获取设备基本信息
   *
   * @param buildInformation HashMap
   */
  public static void getBuildInfo(HashMap buildInformation) {
    Field[] fields = Build.class.getDeclaredFields();
    for (Field field : fields) {
      try {
        field.setAccessible(true);
        buildInformation.put(field.getName(), field.get(null).toString());
      } catch (Exception e) {
        LogUtil.error(e.getMessage());
      }
    }
  }

  /**
   * getPhoneIMEI 获取手机电话信息
   *
   * @param context          Context
   * @param buildInformation HashMap
   */
  public static void getPhoneIMEI(Context context, HashMap buildInformation) {
    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context
      .TELEPHONY_SERVICE);
    String imei = null, imsi = null, phone = null;
    try {
      imei = telephonyManager.getDeviceId();
      imsi = telephonyManager.getSubscriberId();
      phone = telephonyManager.getLine1Number();
    } catch (SecurityException e) {
      LogUtil.error(e.getMessage());
    } catch (Exception e) {
      LogUtil.error(e.getMessage());
    }

    if (!(imei == null || imei.length() <= 0)) {
      buildInformation.put("imei", imei);
    }
    if (!(imsi == null || imsi.length() <= 0)) {
      buildInformation.put("imsi", imsi);
    }
    if (!(phone == null || phone.length() <= 0)) {
      buildInformation.put("phone", phone);
    }
  }

  /**
   * getMacAddress 获取MAC 地址
   *
   * @param context          Context
   * @param buildInformation HashMap
   */
  public static void getMacAddress(Context context, HashMap buildInformation) {
    String macAddress = null;
    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService
      (Context.WIFI_SERVICE);
    WifiInfo wifiInfo = (null == wifiManager ? null : wifiManager.getConnectionInfo());

    if (wifiInfo.getMacAddress().equals(marshmallowMacAddress)) {
      try {
        macAddress = getMacAddressByInterface();
        if (macAddress != null && macAddress.length() > 0) {
          buildInformation.put("MAC", macAddress);
          return;
        } else {
          macAddress = getMacAddressByFile(wifiManager);
          if (macAddress != null && macAddress.length() > 0) {
            buildInformation.put("MAC", macAddress);
          }
          return;
        }
      } catch (IOException e) {
        LogUtil.error(e.getMessage());
      } catch (Exception e) {
        LogUtil.error(e.getMessage());
      }
    }
  }

  /**
   * getMacAddressByInterface
   *
   * @return String
   */
  private static String getMacAddressByInterface() {
    try {
      List<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface networkInterface : list) {
        if (networkInterface.getName().equalsIgnoreCase("wlan0") || networkInterface.getName()
          .equalsIgnoreCase("eth0")) {
          byte[] macBytes = networkInterface.getHardwareAddress();
          if (macBytes == null) {
            return null;
          }
          StringBuilder stringBuilder = new StringBuilder();
          for (byte b : macBytes) {
            stringBuilder.append(String.format("%02X:", b));
          }
          if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
          }
          return stringBuilder.toString();
        }
      }
    } catch (Exception e) {
      LogUtil.error(e.getMessage());
    }
    return null;
  }

  /**
   * getMacAddressByFile
   *
   * @param wifiManager WifiManager
   * @return String
   * @throws Exception e
   */
  private static String getMacAddressByFile(WifiManager wifiManager) throws Exception {
    String ret;

    int wifiState = wifiManager.getWifiState();

    wifiManager.setWifiEnabled(true);
    File file = new File(fileAddressMac);
    FileInputStream fileInputStream = new FileInputStream(file);
    ret = convertRunChifyStringFromStream(fileInputStream);
    fileInputStream.close();

    boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
    wifiManager.setWifiEnabled(enabled);
    return ret;
  }

  /**
   * convertRunChifyStringFromStream
   *
   * @param inputStream InputStream
   * @return String
   * @throws IOException e
   */
  private static String convertRunChifyStringFromStream(InputStream inputStream) throws
    IOException {
    if (inputStream != null) {
      Writer writer = new StringWriter();
      char[] chars = new char[2048];
      try {
        Reader reader = new BufferedReader((new InputStreamReader(inputStream, "UTF-8")));
        int counter;
        while ((counter = reader.read(chars)) != -1) {
          writer.write(chars, 0, counter);
        }

      } finally {
        inputStream.close();
      }
      return writer.toString();
    } else {
      return null;
    }
  }

  /**
   * getVersionCode 获取当前版本CODE
   *
   * @param context Context
   * @return String
   */
  public static String getVersionCode(Context context) {
    PackageManager packageManager = context.getPackageManager();
    PackageInfo packageInfo;
    String versionCode = null;

    try {
      packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      versionCode = packageInfo.versionCode + "";
    } catch (PackageManager.NameNotFoundException e) {
      LogUtil.error(e.getMessage());
    }
    return versionCode;
  }

  /**
   * getVersionName 获取当前版本
   *
   * @param context Context
   * @return String
   */
  public static String getVersionName(Context context) {
    PackageManager packageManager = context.getPackageManager();
    PackageInfo packageInfo;
    String versionName = null;
    try {
      packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      versionName = packageInfo.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      LogUtil.error(e.getMessage());
    }
    return versionName;
  }

  /**
   * setListViewHeightBasedOnChildren ScrollView 嵌套 ListView 内容显示不全
   *
   * @param listView
   */
  public static void setListViewHeightBasedOnChildren(ListView listView) {
    ListAdapter listAdapter = listView.getAdapter();

    if (listAdapter == null) {
      return;
    }


    int totalHeight = 0;
    for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   //listAdapter.getCount()返回数据项的数目
      View listItem = listAdapter.getView(i, null, listView);
      listItem.measure(0, 0);  //计算子项View 的宽高
      totalHeight += listItem.getMeasuredHeight();  //统计所有子项的总高度
    }

    ViewGroup.LayoutParams params = listView.getLayoutParams();
    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    //listView.getDividerHeight()获取子项间分隔符占用的高度
    //params.height最后得到整个ListView完整显示需要的高度
    listView.setLayoutParams(params);

  }
}
