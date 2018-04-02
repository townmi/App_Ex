package com.goubaa.harry.nightplus.Views;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.CustomViews.ShadeView;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.SessionApplication;
import com.goubaa.harry.nightplus.Views.Explores.ExploresFragment;
import com.goubaa.harry.nightplus.Views.Me.MeFragment;
import com.goubaa.harry.nightplus.Views.Mission.MissionFragment;
import com.goubaa.harry.nightplus.Views.VenueList.VenueListFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, ExploresFragment.OnFragmentInteractionListener, MissionFragment.OnFragmentInteractionListener,
  VenueListFragment.OnFragmentInteractionListener, MeFragment.OnFragmentInteractionListener {

  private PopupWindow popupWindow;

  @BindView(R.id.main_activity)
  LinearLayout linearLayout;

  @BindView(R.id.main_viewpager)
  ViewPager viewPager;

  @OnClick(R.id.title_popup)
  public void showMenu(View v) {
    if (popupWindow != null && popupWindow.isShowing()) {
      popupWindow.dismiss();
      return;
    }

    int y = getResources().getDimensionPixelSize(R.dimen.y30);
    View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_drop_down, null);
    popupWindow = new PopupWindow(MainActivity.this);
    popupWindow.setContentView(contentView);
    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//    popupWindow.setOutsideTouchable(true);
//    popupWindow.setAnimationStyle(R.style.animation_main_menu);
//    popupWindow.update();
    popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
    popupWindow.setTouchInterceptor(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (popupWindow.isShowing()) {
          popupWindow.dismiss();
          return true;
        }
        return false;
      }
    });

    popupWindow.showAsDropDown(v, 0, -1 * y);
  }

  //  @BindView(R.id.main_title_bar)
  //  Toolbar toolbar;

  private List<TabFragment> tabFragments;

  private List<ShadeView> tabIndicators;

  private List<Fragment> fragmentList;

  private int[] activeList = {R.drawable.main_tab_explore_active, R.drawable.main_tab_incentives_active, R.drawable.main_tab_mission_active, R.drawable.main_tab_me_active};

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
//    if (position == 2) {
//        tabIndicators.get(position).setIconBitmap(this, R.mipmap.ic_launcher);
//    } else {
//        tabIndicators.get(2).setIconBitmap(this, R.mipmap.ic_launcher);
//    }
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

//    setSupportActionBar(toolbar);

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

    /**
     * 权限申请
     */
    MainActivityPermissionsDispatcher.requestWriteStoragePermissionWithPermissionCheck(this);
    MainActivityPermissionsDispatcher.requestGPSPermissionWithPermissionCheck(this);
  }

  private void initData() {
    tabFragments = new ArrayList<>();
    tabIndicators = new ArrayList<>();
    String[] titles = new String[]{"微信", "通讯录", "发现", "我"};

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_group) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setIconEnable(Menu menu, boolean enable) {
    if (menu != null) {
      try {
        Class clazz = menu.getClass();
        if (clazz.getSimpleName().equals("MenuBuilder")) {
          Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
          m.setAccessible(true);
          //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
          m.invoke(menu, enable);
        }
      } catch (Exception e) {
        LogUtil.error(e.getMessage());
      }
    }
  }

//  @Override
//  protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//    setIconEnable(menu, true);
////    return super.onPrepareOptionsPanel(view, menu);
//  }

  /**
   * ask permission for write Storage
   */
  @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
  void requestWriteStoragePermission() {
//    SessionApplication.callStoragePermission(MainActivity.this);
  }

  @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
  void showAllowForWriteStorage(final PermissionRequest request) {
    new AlertDialog.Builder(this).setMessage("quanxiang").setPositiveButton("allow", (dialog, button) -> request.proceed()).setNegativeButton("cancel", (dialog, button) -> request.cancel()).show();
  }

  @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
  void deniedForWriteStorage() {
    SessionApplication.setCanWriteStorage(false);
    LogUtil.debug("deniedForWriteStorage");
  }

  @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
  void neverAskForWriteStorage() {
    SessionApplication.setCanWriteStorage(true);
    LogUtil.debug("neverAskForWriteStorage");
  }

  /**
   * ask permission for GPS
   */
  @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
  void requestGPSPermission() {

  }

  @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
  void showAllowForGPS(final PermissionRequest request) {
    new AlertDialog.Builder(this).setMessage("GPS").setPositiveButton("allow", (dialog, button) -> request.proceed()).setNegativeButton("cancel", (dialog, which) -> request.cancel()).show();
  }

  @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
  void deniedForGPS() {
    LogUtil.debug("deniedForGPS");
  }

  @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
  void neverAskForGPS() {
    LogUtil.debug("neverAskForGPS");
  }


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
  }
}
