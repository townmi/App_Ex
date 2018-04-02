package com.goubaa.harry.nightplus.Views.Location;

import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseObserver;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.SwipeBackLayout;
import com.goubaa.harry.nightplus.CustomViews.SwipeBack.app.SwipeBackActivity;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.R;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class Location extends SwipeBackActivity {

  @BindView(R.id.location_lists)
  ListView lists;

  @BindView(R.id.location_title)
  LinearLayout title;

  @BindView(R.id.location_arrow_left)
  TextView arrow;

  private int height;
  private Typeface typeface;
  private SwipeBackLayout swipeBackLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    ButterKnife.bind(this);

    getCities();

    Window window = getWindow();
    WindowManager.LayoutParams attrs = window.getAttributes();
    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    attrs.flags |= flagTranslucentStatus;
    window.setAttributes(attrs);

    Resources resources = getResources();
    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    int statusBarHeight = resources.getDimensionPixelSize(resourceId);

    height = statusBarHeight + resources.getDimensionPixelSize(R.dimen.x36);

    typeface = Typeface.createFromAsset(resources.getAssets(), "ionicons.ttf");
    arrow.setTypeface(typeface);

    arrow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Location.this.finish();
      }
    });

    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) title.getLayoutParams();
    layoutParams.height = height;
    title.setLayoutParams(layoutParams);
    title.setPadding(0, statusBarHeight, 0, 0);
    lists.setPadding(0, height + resources.getDimensionPixelSize(R.dimen.y8), 0, 0);

    swipeBackLayout = getSwipeBackLayout();
    swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    swipeBackLayout.setEnableGesture(true);
  }


  private void getCities() {
    Observable<BaseEntity<City>> observable = RetrofitFactory.getVenuesCoreRetrofitService().getCities();
    observable.compose(compose(this.<BaseEntity<City>>bindToLifecycle())).subscribe(new BaseObserver<City>(getContext()) {
      @Override
      protected void onHandleSuccess(ArrayList<City> list) {
        LogUtil.debug("test");
        try {
          if (list != null) {
            LocationItemAdapter locationItemAdapter = new LocationItemAdapter(Location.this, R.layout.activity_location_item, list);
            lists.setAdapter(locationItemAdapter);
          }
        } catch (Exception e) {
          LogUtil.error(e.getMessage());
        }
      }

      @Override
      public void onError(Throwable e) {
        super.onError(e);
      }

      @Override
      protected void onHandleError(String msg) {
        super.onHandleError(msg);
      }
    });
  }
}
