package com.goubaa.harry.nightplus.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.goubaa.harry.nightplus.Views.Explores.ExploresPageFragment;

/**
 * Created by harry on 2017/12/8.
 */

public class ExploresViewPagerAdapter extends FragmentStatePagerAdapter {
  private String[] mTitles;

  public ExploresViewPagerAdapter(FragmentManager fm, String[] mTitles) {
    super(fm);
    this.mTitles = mTitles;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mTitles[position];
  }

  @Override
  public Fragment getItem(int position) {
    return ExploresPageFragment.newInstance(null, null);
  }

  @Override
  public int getCount() {
    return mTitles.length;
  }
}
