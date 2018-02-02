package com.goubaa.harry.nightplus;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.Utils;
import com.goubaa.harry.nightplus.Models.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Consumer;

public class SessionApplication extends Application {

  public static Context mContext;

  public static User user;

  @Override
  public void onCreate() {
    super.onCreate();
    mContext = getApplicationContext();
    getUserInfo();
  }

  public static User getUser() {
    return user;
  }

  public static void setUser(User user) {
    SessionApplication.user = user;
  }

  public void getUserInfo() {
    Log.i("TAG", "START");
    Observable<BaseEntityObject<User>> observable = RetrofitFactory.getUserRetrofitService()
      .getUserInfo();
    observable.subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
      @Override
      public void accept(Disposable disposable) throws Exception {
        if (!Utils.isNetworkAvailable(mContext)) {
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
        Log.i("TAG", user.getId());
      }

      @Override
      public void onError(Throwable e) {
        Log.i("TAG", e.getMessage());
      }

      @Override
      public void onComplete() {
        Log.i("TAG", "onCompleted");
      }
    });
  }
}
