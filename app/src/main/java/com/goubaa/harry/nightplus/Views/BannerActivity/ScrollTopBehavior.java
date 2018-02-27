/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by harry on 2018/1/10.
 */

public class ScrollTopBehavior extends CoordinatorLayout.Behavior<View> {

  int offsetTotal = 0;
  boolean scrolling = false;

  public ScrollTopBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View
    child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
    return true;
  }

  @Override
  public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                             @NonNull View target, int dxConsumed, int dyConsumed, int
                                 dxUnconsumed, int dyUnconsumed, int type) {
    offset(child, dyConsumed);
  }

  public void offset(View view, int dy) {
    int old = offsetTotal;
    int top = offsetTotal - dy;
    top = Math.max(top, -view.getHeight());
    top = Math.min(top, 0);

    offsetTotal = top;
    if (old == offsetTotal) {
      scrolling = false;
      return;
    }
    int delta = offsetTotal - old;
    view.offsetTopAndBottom(delta);
    scrolling = true;
  }

}
