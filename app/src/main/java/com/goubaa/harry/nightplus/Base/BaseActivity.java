package com.goubaa.harry.nightplus.Base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.goubaa.harry.nightplus.Library.Utils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by harry on 2017/12/6.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
  /**
   * @param lifecycleTransformer
   * @param <T>
   * @return
   */
  protected <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycleTransformer) {
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
          .subscribeOn(Schedulers.io())
          .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
              if (!Utils.isNetworkAvailable(BaseActivity.this)) {
                Toast.makeText(BaseActivity.this, "error", Toast.LENGTH_SHORT).show();
              }
            }
          })
          .observeOn(AndroidSchedulers.mainThread())
          .compose(lifecycleTransformer);
      }
    };
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * 弹出toast 显示时长short
   *
   * @param pMsg
   */
  protected void toast(String pMsg) {
    // T.show(this, pMsg, Toast.LENGTH_SHORT);
  }

  /**
   * 根据传入的类(class)打开指定的activity
   *
   * @param pClass
   */
  protected void startThActivity(Class<?> pClass) {
    Intent _Intent = new Intent();
    _Intent.setClass(this, pClass);
    startActivity(_Intent);
//        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
  }

  protected void startThActivityByIntent(Intent pIntent) {
    startActivity(pIntent);
//        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
  }


  @Override
  public void showProgress(String message) {

  }

  @Override
  public void showProgress() {

  }

  @Override
  public void cancelProgress() {

  }

  @Override
  public void showTheToast(int resId) {

  }

  @Override
  public void showTheToast(String msg) {

  }

  @Override
  public Context getContext() {
    return null;
  }

  @Override
  public void onServerFail(String msg) {

  }

  @TargetApi(19)
  protected void setTranslucentStatus(boolean on) {
    Window win = getWindow();
    WindowManager.LayoutParams winParams = win.getAttributes();
    final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    if (on) {
      winParams.flags |= bits;
    } else {
      winParams.flags &= ~bits;
    }
    win.setAttributes(winParams);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
    int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }
}
