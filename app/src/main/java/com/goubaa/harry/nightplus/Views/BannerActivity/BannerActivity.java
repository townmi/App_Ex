/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.BaseObserverObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
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

public class BannerActivity extends BaseActivity {

  @BindView(R.id.explores_banner)
  Banner banner;

  @BindView(R.id.explores_top_buttons)
  LinearLayout buttons;

  @BindView(R.id.explores_list)
  ListView listView;

  private List<String> images = new ArrayList<>();
  private List<String> titles = new ArrayList<>();

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


    int height = statusBarHeight + getResources().getDimensionPixelSize(R.dimen.y24);

    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) buttons
      .getLayoutParams();
    layoutParams.height = height;
    buttons.setLayoutParams(layoutParams);
    buttons.setPadding(0, statusBarHeight, 0, 0);


//    Blurry.with(BannerActivity.this).radius(25).sampling(2).onto(relativeLayout);
    //

//    BlurDrawable blurDrawable = new BlurDrawable(banner);
//    blurDrawable.setBlurRadius(8);
//    blurDrawable.setDownsampleFactor(8);
//    blurDrawable.setOverlayColor(getResources().getColor(R.color
// .main_explores_top_bar_background));
//    blurDrawable.setDrawOffset(0, 0);
//    relativeLayout.setBackgroundDrawable(blurDrawable);

    /**
     * fetch banners
     */
    String query = "{view(isDisplayed:true,sectionType:\"community-banner\"," +
      "cityIds:[\"58d1ecade841a18ba5399026\"]){count," + "rows{_id,title,viewType,url,image, " +
      "articleId, subTitle," + "topic{id,topicName}}}}";
    getBanners(query);

    getExplorePosts("58d1ecade841a18ba5399026");

  }

  private void getBanners(String query) {
    Observable<BaseEntityObject<ExproleBanner>> observable = RetrofitFactory
      .getCmsRetorfitService().getBanners(query);
    observable.compose(compose(this.<BaseEntityObject<ExproleBanner>>bindToLifecycle()))
      .subscribe(new BaseObserverObject<ExproleBanner>(getContext()) {
      @Override
      protected void onHandleSuccess(ExproleBanner exproleBanner) {
        ExproleBanner.ViewBean<CmsView> view = exproleBanner.getView();
        List<CmsView> bannerList = view.getRows();
        for (int i = 0; i < bannerList.size(); i++) {
          CmsView banner = bannerList.get(i);
          images.add(banner.getImage());
          titles.add(banner.getTitle());
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.setImageLoader(new GlideImageLoader()).start();
      }
    });
  }

  private void getExplorePosts(String cityId) {
    Observable<BaseEntityObject<ExprolePosts>> observable = RetrofitFactory
      .getExplorePostsRetorfitService().getExplorePosts("-createdAt", 10, 0, cityId, true);
    observable.compose(compose(this.<BaseEntityObject<ExprolePosts>>bindToLifecycle())).subscribe
      (new BaseObserverObject<ExprolePosts>(getContext()) {
      @Override
      protected void onHandleSuccess(ExprolePosts exprolePosts) {
        List<SearchPost> list = exprolePosts.getHits();
        try {
          if (list != null) {
            ExploresPostViewItemAdapter exploresPostViewItemAdapter = new
              ExploresPostViewItemAdapter(BannerActivity.this, R.layout.activity_banner_post, list);
            listView.setAdapter(exploresPostViewItemAdapter);
          }
        } catch (NullPointerException e) {
          LogUtil.error(e.getMessage());
        }
      }
    });
  }
}
