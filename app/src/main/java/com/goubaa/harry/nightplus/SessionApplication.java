package com.goubaa.harry.nightplus;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Library.Utils;
import com.goubaa.harry.nightplus.Models.User;

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
  public static User user;
  public static SharedPreferences sharedPreferences;
  public static HashMap buildInfomation;
  public static Boolean canWriteStorage;

  @Override
  public void onCreate() {
    super.onCreate();
    mContext = getApplicationContext();
    buildInfomation = new HashMap<>();
    canWriteStorage = false;
    /**
     * 应用 数据存储
     */
    sharedPreferences = mContext.getSharedPreferences("NIGHT_PLUS", mContext.MODE_PRIVATE);
    getUserInfo();
    Utils.getBuildInfo(buildInfomation);
    Utils.getPhoneIMEI(mContext, buildInfomation);
    Utils.getMacAddress(mContext, buildInfomation);

    String versionCode = Utils.getVersionCode(mContext);
    String versionName = Utils.getVersionName(mContext);

    if (versionCode != null && versionCode.length() > 0) {
      buildInfomation.put("VERSION_CODE", versionCode);
    }
    if (versionName != null && versionName.length() > 0) {
      buildInfomation.put("VERSION_NAME", versionName);
    }

    Iterator<Map.Entry<String, String>> iterator = buildInfomation.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      LogUtil.debug("key= " + entry.getKey() + "; value= " + entry.getValue());
    }
  }

  public static User getUser() {
    return user;
  }

  public static void setUser(User user) {
    SessionApplication.user = user;
  }

  /**
   * 使用token 缓存登录用户信息
   */
  public void getUserInfo() {
    Observable<BaseEntityObject<User>> observable = RetrofitFactory.getUserRetrofitService()
      .getUserInfo();
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

  public static void setCanWriteStorage(Boolean canWriteStorage) {
    SessionApplication.canWriteStorage = canWriteStorage;
  }
}
