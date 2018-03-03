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
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.BaseObserverObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.CustomViews.ShadeView;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.CmsView;
import com.goubaa.harry.nightplus.Models.ExprolePosts;
import com.goubaa.harry.nightplus.Models.SearchPost;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.banner.Banner;
import com.goubaa.harry.banner.BannerConfig;
import com.goubaa.harry.nightplus.Models.ExproleBanner;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class BannerActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View
  .OnClickListener {

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
    titles.add("最新");
    titles.add("最热");

    tab.getTabAt(0).setText(titles.get(0));
    tab.getTabAt(1).setText(titles.get(1));

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
    viewPager.setCurrentItem(0, false);
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