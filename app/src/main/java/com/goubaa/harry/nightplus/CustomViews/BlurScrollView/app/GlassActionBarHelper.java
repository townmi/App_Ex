package com.goubaa.harry.nightplus.CustomViews.BlurScrollView.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.goubaa.harry.nightplus.R;

import com.goubaa.harry.nightplus.CustomViews.BlurScrollView.NotifyingScrollView;
import com.goubaa.harry.nightplus.CustomViews.BlurScrollView.NotifyingScrollView.OnScrollChangedListener;
import com.goubaa.harry.nightplus.CustomViews.BlurScrollView.app.ListViewScrollObserver.OnListViewScrollListener;

public class GlassActionBarHelper implements ViewTreeObserver.OnGlobalLayoutListener, OnScrollChangedListener, BlurTask.Listener, OnListViewScrollListener {
  private int contentLayout;
  private FrameLayout frame;
  private View content;
  private ListAdapter adapter;
  private ImageView blurredOverlay;
  private int actionBarHeight = 0;
  private int width;
  private int height;
  private Bitmap scaled;
  private int blurRadius = GlassActionBar.DEFAULT_BLUR_RADIUS;
  private BlurTask blurTask;
  private int lastScrollPosition = -1;
  private NotifyingScrollView scrollView;
  private ListView listView;
  private int downSampling = GlassActionBar.DEFAULT_DOWNSAMPLING;
  private static final String TAG = "GlassActionBarHelper";
  private Drawable windowBackground;
  private boolean stop = false;

  public GlassActionBarHelper contentLayout(int layout) {
    this.contentLayout = layout;
    return this;
  }

  public GlassActionBarHelper contentLayout(int layout, int height) {
    this.contentLayout = layout;
    this.actionBarHeight = height;
    return this;
  }

  public GlassActionBarHelper contentLayout(int layout, ListAdapter adapter) {
    this.contentLayout = layout;
    this.adapter = adapter;
    return this;
  }

  public View createView(Context context) {
    int[] attrs = {android.R.attr.windowBackground};

    // Need to get resource id of style pointed to from actionBarStyle
    TypedValue outValue = new TypedValue();
    context.getTheme().resolveAttribute(android.R.attr.windowBackground, outValue, true);

    TypedArray style = context.getTheme().obtainStyledAttributes(outValue.resourceId, attrs);
    windowBackground = style.getDrawable(0);
    style.recycle();

    LayoutInflater inflater = LayoutInflater.from(context);
    frame = (FrameLayout) inflater.inflate(R.layout.fragment_blur, null);
    content = inflater.inflate(contentLayout, (ViewGroup) frame, false);
    frame.addView(content, 0);


    frame.getViewTreeObserver().addOnGlobalLayoutListener(this);

    blurredOverlay = (ImageView) frame.findViewById(R.id.blurredOverlay);

    if (content instanceof NotifyingScrollView) {
      scrollView = (NotifyingScrollView) content;
      scrollView.setOnScrollChangedListener(this);
    } else if (content instanceof ListView) {
      listView = (ListView) content;
      listView.setAdapter(adapter);
      ListViewScrollObserver observer = new ListViewScrollObserver(listView);
      observer.setOnScrollUpAndDownListener(this);
    }

    if (actionBarHeight == 0) {
      actionBarHeight = getActionBarHeight(context);
    }

    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) blurredOverlay.getLayoutParams();
    layoutParams.height = actionBarHeight;
    blurredOverlay.setLayoutParams(layoutParams);

    return frame;
  }

  public void invalidate() {
    if (this.stop) {
      return;
    }
    scaled = null;
    computeBlurOverlay();
    updateBlurOverlay(lastScrollPosition, true);
  }

  public void setStop(boolean s) {
    this.stop = s;
  }

  public void setBlurRadius(int newValue) {
    if (!GlassActionBar.isValidBlurRadius(newValue)) {
      throw new IllegalArgumentException("Invalid blur radius");
    }
    if (blurRadius == newValue) {
      return;
    }
    blurRadius = newValue;
    invalidate();
  }

  public int getBlurRadius() {
    return blurRadius;
  }

  public void setDownsampling(int newValue) {
    if (!GlassActionBar.isValidDownsampling(newValue)) {
      throw new IllegalArgumentException("Invalid downsampling");
    }
    if (downSampling == newValue) {
      return;
    }
    downSampling = newValue;
    invalidate();
  }

  public int getDownsampling() {
    return downSampling;
  }

  protected int getActionBarHeight(Context context) {
    TypedValue outValue = new TypedValue();
    context.getTheme().resolveAttribute(android.R.attr.actionBarSize, outValue, true);
    return context.getResources().getDimensionPixelSize(outValue.resourceId);
  }

  @Override
  public void onGlobalLayout() {
    if (width != 0) {
      return;
    }
    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(frame.getWidth(), View.MeasureSpec.AT_MOST);
    int heightMeasureSpec;
    if (listView != null) {
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(frame.getHeight(), View.MeasureSpec.EXACTLY);
    } else {
      int size = 0;
      if (LinearLayout.LayoutParams.WRAP_CONTENT > 0) {
        size = LinearLayout.LayoutParams.WRAP_CONTENT;
      }
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.UNSPECIFIED);
    }
    content.measure(widthMeasureSpec, heightMeasureSpec);
    width = frame.getWidth();
    height = content.getMeasuredHeight();
    lastScrollPosition = scrollView != null ? scrollView.getScrollY() : 0;
    invalidate();
  }

  private void computeBlurOverlay() {
    if (scaled != null) {
      return;
    }

    int scrollPosition = 0;
    if (scrollView != null) {
      scrollPosition = scrollView.getScrollY();
    }
    long start = System.nanoTime();

    scaled = Utils.drawViewToBitmap(scaled, content, width, height, downSampling, windowBackground);

    long delta = System.nanoTime() - start;

    startBlurTask();

    if (scrollView != null) {
      scrollView.scrollTo(0, scrollPosition);
    }
  }

  private void startBlurTask() {
    if (blurTask != null) {
      blurTask.cancel();
    }
    blurTask = new BlurTask(frame.getContext(), this, scaled, blurRadius);
  }

  private void updateBlurOverlay(int top, boolean force) {
    if (scaled == null) {
      return;
    }

    if (top < 0) {
      top = 0;
    }
    if (!force && lastScrollPosition == top) {
      return;
    }
    lastScrollPosition = top;
    Bitmap actionBarSection = Bitmap.createBitmap(scaled, 0, top / downSampling, width / downSampling, actionBarHeight / downSampling);
    // Blur here until background finished (will make smooth jerky during the first second or so).
    Bitmap blurredBitmap;
    if (isBlurTaskFinished()) {
      blurredBitmap = actionBarSection;
    } else {
      blurredBitmap = Blur.apply(frame.getContext(), actionBarSection);
    }
    Bitmap enlarged = Bitmap.createScaledBitmap(blurredBitmap, width, actionBarHeight, false);
    blurredBitmap.recycle();
    actionBarSection.recycle();
    blurredOverlay.setImageBitmap(enlarged);
  }

  private boolean isBlurTaskFinished() {
    return blurTask == null;
  }

  @Override
  public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
    // ScrollView scroll
    onNewScroll(t);
  }

  @Override
  public void onScrollUpDownChanged(int delta, int scrollPosition, boolean exact) {
    // ListView scroll
    if (exact) {
      onNewScroll(-scrollPosition);
    }
  }

  private void onNewScroll(int t) {
    updateBlurOverlay(t, false);
  }

  @Override
  public void onBlurOperationFinished() {
    blurTask = null;
    updateBlurOverlay(lastScrollPosition, true);
    // Utils.saveToSdCard(scaled, "blurred.png");
  }

  @Override
  public void onScrollIdle() {
  }
}

