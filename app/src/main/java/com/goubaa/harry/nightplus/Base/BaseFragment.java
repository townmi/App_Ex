package com.goubaa.harry.nightplus.Base;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.goubaa.harry.nightplus.Library.Utils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import com.goubaa.harry.nightplus.Library.CustomToast;
import com.goubaa.harry.nightplus.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by harry on 2017/12/8.
 */

public abstract class BaseFragment extends RxFragment implements IBaseView {
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
              if (!Utils.isNetworkAvailable(getContext())) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
              }
            }
          })
          .observeOn(AndroidSchedulers.mainThread())
          .compose(lifecycleTransformer);
      }
    };
  }

  private Toast mToast;

  public void toast(String msg) {
    CustomToast.showShort(getActivity(), msg);
  }

  public void onResume() {
    super.onResume();
  }

  public void onPause() {
    super.onPause();
  }

  public abstract boolean hasMultiFragment();

  protected abstract String setFragmentName();

  @Override
  public void showProgress(String message) {

  }

  @Override
  public void showProgress() {
    showProgress("");
  }

  @Override
  public void cancelProgress() {

  }

  @Override
  public void showTheToast(int resId) {
    showTheToast(getString(resId));
  }

  @Override
  public void showTheToast(String msg) {
    if (mToast == null) {
      mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
    } else {
      mToast.setText(msg);
      mToast.setDuration(Toast.LENGTH_SHORT);
    }
    mToast.show();
  }

  @Override
  public Context getContext() {
    return getActivity();
  }

  @Override
  public void onServerFail(String msg) {

  }

  protected void startThActivity(Class<?> pClass) {
    Intent intent = new Intent();

    intent.setClass(getActivity(), pClass);
    startActivity(intent);
    getActivity().overridePendingTransition(R.transition.transition_next_in, R.transition.transition_next_out);
  }

  protected void startThActivityByIntent(Intent intent) {
    startActivity(intent);
    getActivity().overridePendingTransition(R.transition.transition_next_in, R.transition.transition_next_out);
  }
}
