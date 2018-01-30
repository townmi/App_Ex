package com.goubaa.harry.nightplus.Base;

import android.content.Context;

/**
 * Created by harry on 2017/12/6.
 */

public interface IBaseView {

  Context getContext();

  /**
   * @param message
   */
  void showProgress(String message);

  void showProgress();

  void cancelProgress();

  /**
   * @param resID 资源ID
   */
  void showTheToast(int resID);

  /**
   * @param message
   */
  void showTheToast(String message);

  void onServerFail(String message);
}
