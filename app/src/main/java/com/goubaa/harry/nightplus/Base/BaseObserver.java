package com.goubaa.harry.nightplus.Base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

  private static final String TAG = "BaseObserver";
  private Context context;

  protected BaseObserver(Context context) {
    this.context = context;
  }

  @Override
  public void onSubscribe(Disposable disposable) {
  }

  @Override
  public void onNext(BaseEntity<T> value) {
    if (value.isSuccess()) {
      ArrayList<T> arrayList = value.getData();
      onHandleSuccess(arrayList);
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

  protected abstract void onHandleSuccess(ArrayList<T> arrayList);

  protected void onHandleError(String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }
}
