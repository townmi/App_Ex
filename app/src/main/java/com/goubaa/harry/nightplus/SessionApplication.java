package com.goubaa.harry.nightplus;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Library.Utils;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Consumer;

public class SessionApplication extends Application {
  /**
   *
   */
  public static Context mContext;
  public static SharedPreferences sharedPreferences;
  public static HashMap buildInformation;
  public static Boolean canWriteStorage;
  public static String token = null;
  public static User user = null;
  public static ArrayList<City> cities = null;

  // 动态广播接收器
  private static BatteryReceiver batteryReceiver;

  @Override
  public void onCreate() {
    super.onCreate();
    mContext = getApplicationContext();
    buildInformation = new HashMap<>();
    canWriteStorage = false;
    /**
     * 应用 数据存储
     */
    sharedPreferences = mContext.getSharedPreferences("NIGHT_PLUS", mContext.MODE_PRIVATE);
    token = sharedPreferences.getString("TOKEN", null);

    getCityList();

    if (token != null && token.length() > 0) {
      getUserInfo();
    }

    Utils.getBuildInfo(buildInformation);
    Utils.getPhoneIMEI(mContext, buildInformation);
    Utils.getMacAddress(mContext, buildInformation);

    String versionCode = Utils.getVersionCode(mContext);
    String versionName = Utils.getVersionName(mContext);

    if (versionCode != null && versionCode.length() > 0) {
      buildInformation.put("VERSION_CODE", versionCode);
    }
    if (versionName != null && versionName.length() > 0) {
      buildInformation.put("VERSION_NAME", versionName);
    }

    // print build information
    Iterator<Map.Entry<String, String>> iterator = buildInformation.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
    }
  }

  public static User getUser() {
    return user;
  }

  public static void setUser(User user) {
    SessionApplication.user = user;
  }

  public static ArrayList<City> getCities() {
    return cities;
  }

  /**
   * 使用token 缓存登录用户信息
   */
  public void getUserInfo() {
    Observable<BaseEntityObject<User>> observable = RetrofitFactory.getUserRetrofitService().getUserInfo();
    observable.subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
      @Override
      public void accept(Disposable disposable) throws Exception {
        if (!Utils.isNetworkAvailable(mContext)) {
          LogUtil.debug("NetWork Disabled");
          Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
        }
      }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntityObject<User>>() {
      @Override
      public void onSubscribe(Disposable d) {
      }

      @Override
      public void onNext(BaseEntityObject<User> userBaseEntityObject) {
        user = userBaseEntityObject.getData();
        LogUtil.info(user.getId());
      }

      @Override
      public void onError(Throwable e) {
        LogUtil.info(e.getMessage());
      }

      @Override
      public void onComplete() {
        LogUtil.info("getUserInfo complete");
      }
    });
  }

  public void getCityList() {
    Observable<BaseEntity<City>> observable = RetrofitFactory.getVenuesCoreRetrofitService().getCities();
    observable.subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
      @Override
      public void accept(Disposable disposable) throws Exception {
        if (!Utils.isNetworkAvailable(mContext)) {
          LogUtil.debug("NetWork Disabled");
          Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
        }
      }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity<City>>() {
      @Override
      public void onSubscribe(Disposable d) {
      }

      @Override
      public void onNext(BaseEntity<City> cityBaseEntity) {
        ArrayList<City> cities = cityBaseEntity.getData();
        LogUtil.info("" + cities.size());
      }

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onComplete() {
        LogUtil.info("getCityLis complete");
      }
    });
  }

  public static void setCanWriteStorage(Boolean canWriteStorage) {
    SessionApplication.canWriteStorage = canWriteStorage;
  }

  public static void getElectricity() {
    /**
     * 如果系统API版本大于等于21, 可以使用 BatteryManager
     */
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//      BatteryManager batteryManager = (BatteryManager) mContext.getSystemService(Context
//        .BATTERY_SERVICE);
    // 电池百分比
//      LogUtil.debug(batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) + "");
//    } else {
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
    batteryReceiver = new BatteryReceiver();
    mContext.registerReceiver(batteryReceiver, intentFilter);
//    }
  }

  protected static void destoryBatteryReceiver() {
    mContext.unregisterReceiver(batteryReceiver);
    LogUtil.debug("unregisterReceiver");
  }
}

class BatteryReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    int level = intent.getExtras().getInt("level");
    int scale = intent.getExtras().getInt("scale");
    int percent = level * 100 / scale;
    LogUtil.debug(percent + "%");
    SessionApplication.destoryBatteryReceiver();
  }
}
