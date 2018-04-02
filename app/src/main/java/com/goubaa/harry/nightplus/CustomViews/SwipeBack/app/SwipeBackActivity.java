package com.goubaa.harry.nightplus.CustomViews.SwipeBack.app;

import android.os.Bundle;
import android.view.View;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.SwipeBackLayout;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.Utils;

public class SwipeBackActivity extends BaseActivity implements SwipeBackActivityBase {
  private SwipeBackActivityHelper mHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHelper = new SwipeBackActivityHelper(this);
    mHelper.onActivityCreate();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mHelper.onPostCreate();
  }

  @Override
  public View findViewById(int id) {
    View v = super.findViewById(id);
    if (v == null && mHelper != null) return mHelper.findViewById(id);
    return v;
  }

  @Override
  public SwipeBackLayout getSwipeBackLayout() {
    return mHelper.getSwipeBackLayout();
  }

  @Override
  public void setSwipeBackEnable(boolean enable) {
    getSwipeBackLayout().setEnableGesture(enable);
  }

  @Override
  public void scrollToFinishActivity() {
    Utils.convertActivityToTranslucent(this);
    getSwipeBackLayout().scrollToFinishActivity();
  }
}
