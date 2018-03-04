/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View
  .OnClickListener, ExploresChoiceFragment.OnFragmentInteractionListener, ExploresFollowFragment.OnFragmentInteractionListener {

  @BindView(R.id.explores_view_pager)
  ViewPager viewPager;

  @BindView(R.id.explores_tab)
  TabLayout tab;

  @BindView(R.id.explores_top_buttons)
  LinearLayout buttons;

  @BindView(R.id.explores_title_location)
  TextView location;

  @BindView(R.id.explores_title_member)
  TextView member;


  private Typeface typeface;
  private List<Fragment> fragmentList;

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.main_tab_one:
        viewPager.setCurrentItem(0, false);
        break;

      case R.id.main_tab_two:
        viewPager.setCurrentItem(1, false);
        break;
    }
  }

  @Override
  public void onPageSelected(int position) {
  }

  @Override
  public void onPageScrollStateChanged(int state) {
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_banner);
    ButterKnife.bind(this);

    /**
     * 设置status Bar 悬浮
     */
    Window window = getWindow();
    WindowManager.LayoutParams attrs = window.getAttributes();
    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    attrs.flags |= flagTranslucentStatus;
    window.setAttributes(attrs);

    Resources resources = getResources();
    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    int statusBarHeight = resources.getDimensionPixelSize(resourceId);


    int height = statusBarHeight + resources.getDimensionPixelSize(R.dimen.x36);

    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) buttons
      .getLayoutParams();
    layoutParams.height = height;
    buttons.setLayoutParams(layoutParams);
    buttons.setPadding(0, statusBarHeight, 0, 0);


    typeface = Typeface.createFromAsset(resources.getAssets(), "ionicons.ttf");
    location.setTypeface(typeface);
    member.setTypeface(typeface);


    ArrayList<String> titles = new ArrayList<>();
    titles.add("");
    titles.add("");

//    tab.addTab(tab.newTab().setText("最新"));
//    tab.addTab(tab.newTab().setText("最热"));

    fragmentList = new ArrayList<>();
    Fragment exploresChoiceFragment = ExploresChoiceFragment.newInstance("", "");
    Fragment exploresFollowFragment = ExploresFollowFragment.newInstance("", "");
    fragmentList.add(exploresChoiceFragment);
    fragmentList.add(exploresFollowFragment);


    viewPager.setOffscreenPageLimit(1);
    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        return fragmentList.get(position);
      }

      @Override
      public int getCount() {
        return fragmentList.size();
      }

      @Override
      public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
      }
    });
    viewPager.addOnPageChangeListener(this);

    tab.setupWithViewPager(viewPager);

    tab.getTabAt(0).setCustomView(getTabChoiceView(0));
    tab.getTabAt(1).setCustomView(getTabFollowView(1));

    viewPager.setCurrentItem(0, false);
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  private View getTabChoiceView(final int position) {
    View view = LayoutInflater.from(BannerActivity.this).inflate(R.layout.activity_banner_tab_item_choice, null);
    return view;
  }

  private View getTabFollowView(final int position) {
    View view = LayoutInflater.from(BannerActivity.this).inflate(R.layout.activity_banner_tab_item_follow, null);
    return view;
  }
}


//    Blurry.with(BannerActivity.this).radius(25).sampling(2).onto(relativeLayout);
//

//    BlurDrawable blurDrawable = new BlurDrawable(banner);
//    blurDrawable.setBlurRadius(8);
//    blurDrawable.setDownsampleFactor(8);
//    blurDrawable.setOverlayColor(getResources().getColor(R.color
// .main_explores_top_bar_background));
//    blurDrawable.setDrawOffset(0, 0);
//    relativeLayout.setBackgroundDrawable(blurDrawable);