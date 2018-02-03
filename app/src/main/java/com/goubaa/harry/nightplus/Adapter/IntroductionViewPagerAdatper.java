package com.goubaa.harry.nightplus.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by harry on 2018/2/2.
 */

public class IntroductionViewPagerAdatper extends PagerAdapter {

  private List<View> views;

  public IntroductionViewPagerAdatper(List<View> views) {
    this.views = views;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    container.addView(views.get(position));
    return views.get(position);
//    return super.instantiateItem(container, position);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView(views.get(position));
//    super.destroyItem(container, position, object);
  }

  @Override
  public int getCount() {
    return views.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}
