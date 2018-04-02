/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.Location.Location;
import com.goubaa.harry.nightplus.Views.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View
  .OnClickListener, ExploresChoiceFragment.OnFragmentInteractionListener, ExploresFollowFragment
  .OnFragmentInteractionListener {

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

  private PopupWindow popupWindow;

  private Animation animationDown;
  private Animation animationUp;
  private TextView arrow;
  private int position;
  private int popupWindowOffsetY;
  private int height;
  private Typeface typeface;
  private List<Fragment> fragmentList;

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.main_tab_one:
        viewPager.setCurrentItem(0, false);
        this.position = 0;
        break;

      case R.id.main_tab_two:
        this.position = 1;
        viewPager.setCurrentItem(1, false);
        break;
    }
  }

  @Override
  public void onPageSelected(int position) {
    this.position = position;
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

    height = statusBarHeight + resources.getDimensionPixelSize(R.dimen.x36);

    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) buttons
      .getLayoutParams();
    layoutParams.height = height;
    buttons.setLayoutParams(layoutParams);
    buttons.setPadding(0, statusBarHeight, 0, 0);

    typeface = Typeface.createFromAsset(resources.getAssets(), "ionicons.ttf");
    location.setTypeface(typeface);
    member.setTypeface(typeface);

    location.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(BannerActivity.this, Location.class);
        startActivity(intent);
      }
    });

    member.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(BannerActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });

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

    tab.getTabAt(0).setCustomView(getTabChoiceView());
    tab.getTabAt(1).setCustomView(getTabFollowView());

    viewPager.setCurrentItem(0, false);
    this.position = 0;

    popupWindowOffsetY = resources.getDimensionPixelSize(R.dimen.x36);
    View contentView = LayoutInflater.from(BannerActivity.this).inflate(R.layout
      .explore_banner_dorp_down, null);
    LinearLayout box = (LinearLayout) contentView.findViewById(R.id.explores_banner_tab_item_box);
//    box.setPadding(popupWindowOffsetY, 0, 0, 0);

    popupWindow = new PopupWindow(BannerActivity.this);
    popupWindow.setContentView(contentView);
    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
    popupWindow.setTouchInterceptor(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
//        if (popupWindow.isShowing()) {
//          BannerActivity.this.arrow.startAnimation(animationUp);
//          popupWindow.dismiss();
//          return true;
//        }
        return false;
      }
    });

    animationDown = AnimationUtils.loadAnimation(BannerActivity.this, R.anim
      .animation_explore_tab_arrow_down);
    animationUp = AnimationUtils.loadAnimation(BannerActivity.this, R.anim
      .animation_explore_tab_arrow_up);
    animationDown.setInterpolator(new LinearInterpolator());
    animationUp.setInterpolator(new LinearInterpolator());
    animationDown.setFillAfter(!animationDown.getFillAfter());
    animationUp.setFillAfter(!animationUp.getFillAfter());
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  private View getTabChoiceView() {
    View view = LayoutInflater.from(BannerActivity.this).inflate(R.layout
      .activity_banner_tab_item_choice, null);
    this.arrow = (TextView) view.findViewById(R.id.explores_banner_tab_arrow);
    this.arrow.setTypeface(typeface);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (BannerActivity.this.position == 0) {
          if (popupWindow != null && popupWindow.isShowing()) {
            BannerActivity.this.arrow.startAnimation(animationUp);
            popupWindow.dismiss();
            return;
          }
          int y = (popupWindowOffsetY - view.getMeasuredHeight()) / 2;
          BannerActivity.this.arrow.startAnimation(animationDown);
//          popupWindow.showAsDropDown(v, 0, -1 * BannerActivity.this.popupWindowOffsetY);
          popupWindow.showAsDropDown(v, 0, y);
        } else {
          viewPager.setCurrentItem(0, true);
        }
      }
    });
    return view;
  }

  private View getTabFollowView() {
    View view = LayoutInflater.from(BannerActivity.this).inflate(R.layout
      .activity_banner_tab_item_follow, null);
    return view;
  }


  private static class TabItem {

    /**
     * string : 玩乐
     * key : event
     */

    private String string;
    private String key;

    public String getString() {
      return string;
    }

    public void setString(String string) {
      this.string = string;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }
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