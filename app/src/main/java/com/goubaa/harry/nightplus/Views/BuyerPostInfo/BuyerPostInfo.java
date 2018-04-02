package com.goubaa.harry.nightplus.Views.BuyerPostInfo;

import com.goubaa.harry.banner.Banner;
import com.goubaa.harry.banner.BannerConfig;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.SwipeBackLayout;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.app.SwipeBackActivity;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.BannerActivity.GlideImageLoader;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyerPostInfo extends SwipeBackActivity {

  @BindView(R.id.buyer_post_banner)
  Banner banner;

  private Post post;
  private SwipeBackLayout swipeBackLayout;
  private List<String> images = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buyer_post_info);
    ButterKnife.bind(this);

    post = (Post) getIntent().getSerializableExtra("post");
    if (post != null) {
      List<Post.DefaultComponentsBean> defaultComponentsBeans = post.getDefaultComponents();
      if (defaultComponentsBeans != null) {
        for (int i = 0; i < defaultComponentsBeans.size(); i++) {
          Post.DefaultComponentsBean _c = defaultComponentsBeans.get(i);
          String _name = _c.getName();
          if (_name.equals("component-banner")) {
            LinkedHashMap<String, ArrayList<String>> l = (LinkedHashMap<String, ArrayList<String>>) defaultComponentsBeans.get(i).getContent();
            if (l.get("banner") != null && l.get("banner") != null) {
              images = l.get("banner");
            }
          }
        }
      }
      if (images != null) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images);
        banner.setImageLoader(new GlideImageLoader()).start();
      }
    }


    Window window = getWindow();
    WindowManager.LayoutParams attrs = window.getAttributes();
    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    attrs.flags |= flagTranslucentStatus;
    window.setAttributes(attrs);

    swipeBackLayout = getSwipeBackLayout();
    swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    swipeBackLayout.setEnableGesture(true);
  }
}
