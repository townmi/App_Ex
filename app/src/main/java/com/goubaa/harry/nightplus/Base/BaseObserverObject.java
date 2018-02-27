

package com.goubaa.harry.nightplus.Base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserverObject<T> implements Observer<BaseEntityObject<T>> {

  private static final String TAG = "BaseObserverObject";
  private Context context;

  protected BaseObserverObject(Context context) {
    this.context = context;
  }

  @Override
  public void onSubscribe(Disposable disposable) {
  }

  @Override
  public void onNext(BaseEntityObject<T> value) {
    if (value.isSuccess()) {
      T t = value.getData();
      onHandleSuccess(t);
    } else {
      onHandleError(value.getStatus());
    }
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, "onError:" + e.toString());
  }

  @Override
  public void onComplete() {
    Log.e(TAG, "onComplete");
  }

  protected abstract void onHandleSuccess(T t);

  protected void onHandleError(String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }
}
