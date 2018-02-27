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

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.BaseObserver;
import com.goubaa.harry.nightplus.Base.BaseObserverObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.banner.Banner;
import com.goubaa.harry.banner.BannerConfig;
import com.goubaa.harry.nightplus.Models.ExproleBanner;
import com.goubaa.harry.nightplus.Models.GraphqlRows;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class BannerActivity extends BaseActivity {

  @BindView(R.id.explores_banner)
  Banner banner;

  @BindView(R.id.explores_top_buttons)
  LinearLayout linearLayout;

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
    int height = resources.getDimensionPixelSize(resourceId) + getResources()
      .getDimensionPixelSize(R.dimen.y25);

    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout
      .getLayoutParams();
    layoutParams.height = height;
    linearLayout.setLayoutParams(layoutParams);


//    Blurry.with(BannerActivity.this).radius(25).sampling(2).onto(relativeLayout);
    //

//    BlurDrawable blurDrawable = new BlurDrawable(banner);
//    blurDrawable.setBlurRadius(8);
//    blurDrawable.setDownsampleFactor(8);
//    blurDrawable.setOverlayColor(getResources().getColor(R.color
// .main_explores_top_bar_background));
//    blurDrawable.setDrawOffset(0, 0);
//    relativeLayout.setBackgroundDrawable(blurDrawable);


    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    images.add("http://ooa2erl8d.bkt.clouddn.com/f2ba339b3dc64ae6bc28484a120bf80a.jpg");
    titles.add("春节假期夜晚玩乐大比拼");

    images.add("http://ooa2erl8d.bkt.clouddn.com/728f1b098f1a44eea3e486d6af27e788.jpg");
    titles.add("征集新年夜晚创意玩法");
    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    banner.setImages(images);
    banner.setBannerTitles(titles);
    banner.setImageLoader(new GlideImageLoader()).start();

    String query = "{view(isDisplayed:true,sectionType:\"community-banner\"," +
      "cityIds:[\"58d1ecade841a18ba5399026\"]){count," + "rows{_id,title,viewType,url,image, " +
      "articleId, subTitle,topic{id,topicName}}}}";
    getBanners(query);

  }

  private void getBanners(String query) {

    Observable<BaseEntityObject<GraphqlRows<ExproleBanner>>> observable = RetrofitFactory
      .getCmsRetorfitService().getBanners(query);
    observable.compose(compose(this.<BaseEntityObject<GraphqlRows<ExproleBanner>>>bindToLifecycle
      ())).subscribe(new BaseObserverObject<GraphqlRows<ExproleBanner>>(getContext()) {
      @Override
      protected void onHandleSuccess(GraphqlRows<ExproleBanner> graphqlRows) {
        int count = graphqlRows.getCount();
        ArrayList<ExproleBanner> arrayList = graphqlRows.getRows();
      }
    });
  }
}
