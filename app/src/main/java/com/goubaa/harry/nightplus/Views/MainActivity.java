package com.goubaa.harry.nightplus.Views;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.CustomViews.ShadeView;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.Explores.ExploresFragment;
import com.goubaa.harry.nightplus.Views.Explores.ExploresPageFragment;
import com.goubaa.harry.nightplus.Views.Me.MeFragment;
import com.goubaa.harry.nightplus.Views.Mission.MissionFragment;
import com.goubaa.harry.nightplus.Views.VenueList.VenueListFragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
  ViewPager.OnPageChangeListener,
  View.OnClickListener,
  ExploresFragment.OnFragmentInteractionListener,
  MissionFragment.OnFragmentInteractionListener,
  VenueListFragment.OnFragmentInteractionListener,
  MeFragment.OnFragmentInteractionListener,
  ExploresPageFragment.OnFragmentInteractionListener {

  @BindView(R.id.main_activity)
  LinearLayout linearLayout;

  @BindView(R.id.main_viewpager)
  ViewPager viewPager;

  private List<TabFragment> tabFragments;

  private List<ShadeView> tabIndicators;

  private List<Fragment> fragmentList;

  private int[] activeList = {
    R.drawable.main_tab_explore_active,
    R.drawable.main_tab_incentives_active,
    R.drawable.main_tab_mission_active,
    R.drawable.main_tab_me_active
  };

  private FragmentPagerAdapter adapter;

  @Override
  public void onClick(View view) {

    resetTabsStatus();

    switch (view.getId()) {
      case R.id.main_tab_one:
        tabIndicators.get(0).setIconAlpha(1.0f);
        viewPager.setCurrentItem(0, false);
        break;

      case R.id.main_tab_two:
        tabIndicators.get(1).setIconAlpha(1.0f);
        viewPager.setCurrentItem(1, false);
        break;

      case R.id.main_tab_three:
        tabIndicators.get(2).setIconAlpha(1.0f);
        viewPager.setCurrentItem(2, false);
        break;

      case R.id.main_tab_four:
        tabIndicators.get(3).setIconAlpha(1.0f);
        viewPager.setCurrentItem(3, false);
        break;
    }
  }

  @Override
  public void onPageSelected(int position) {
//        if (position == 2) {
//            tabIndicators.get(position).setIconBitmap(this, R.mipmap.ic_launcher);
//        } else {
//            tabIndicators.get(2).setIconBitmap(this, R.mipmap.ic_launcher);
//        }
  }

  @Override
  public void onPageScrollStateChanged(int state) {
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if (positionOffset > 0) {
      ShadeView leftTab = tabIndicators.get(position);
      ShadeView rightTab = tabIndicators.get(position + 1);
      leftTab.setIconAlpha(1 - positionOffset);
      rightTab.setIconAlpha(positionOffset);
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initData();
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

    // 修改 顶部状态栏的颜色
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.BLACK);
    }
  }

  private void initData() {
    tabFragments = new ArrayList<>();
    tabIndicators = new ArrayList<>();
    String[] titles = new String[]{"市场", "交易", "资金", "我的"};

    //
    Fragment exploresFragment = ExploresFragment.newInstance();
    Fragment meFragment = MeFragment.newInstance("", "");
    Fragment missionFragment = MissionFragment.newInstance("", "");
    Fragment venueListFragment = VenueListFragment.newInstance(null, null);

    fragmentList = new ArrayList<>();

    fragmentList.add(exploresFragment);
    fragmentList.add(venueListFragment);
    fragmentList.add(missionFragment);
    fragmentList.add(meFragment);

    viewPager.setOffscreenPageLimit(1);
    viewPager.setCurrentItem(0, false);

    //
    for (String title : titles) {
      TabFragment tabFragment = new TabFragment();
      Bundle bundle = new Bundle();
      bundle.putString("Title", title);
      tabFragment.setArguments(bundle);
      tabFragments.add(tabFragment);
    }
    initTabIndicator();
  }

  private void initTabIndicator() {
    ShadeView one = (ShadeView) findViewById(R.id.main_tab_one);
    ShadeView two = (ShadeView) findViewById(R.id.main_tab_two);
    ShadeView three = (ShadeView) findViewById(R.id.main_tab_three);
    ShadeView four = (ShadeView) findViewById(R.id.main_tab_four);

    tabIndicators.add(one);
    tabIndicators.add(two);
    tabIndicators.add(three);
    tabIndicators.add(four);

    one.setOnClickListener(this);
    two.setOnClickListener(this);
    three.setOnClickListener(this);
    four.setOnClickListener(this);
    resetTabsStatus();

    one.setIconAlpha(1.0f);
  }

  private void resetTabsStatus() {
    for (int i = 0; i < tabIndicators.size(); i++) {
      tabIndicators.get(i).setIconAlpha(0);
    }
  }

  public void onFragmentInteraction(Uri uri) {
    //you can leave it empty
  }

}
