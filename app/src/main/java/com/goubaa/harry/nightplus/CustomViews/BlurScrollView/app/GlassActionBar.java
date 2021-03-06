package com.goubaa.harry.nightplus.CustomViews.BlurScrollView.app;

/**
 * Created by harry on 2018/3/9.
 */

public class GlassActionBar {
//  public static boolean verbose = true;

  public static final int DEFAULT_BLUR_RADIUS = 10;
  public static final int MIN_BLUR_RADIUS = 1;
  public static final int MAX_BLUR_RADIUS = 20;

  public static final int DEFAULT_DOWNSAMPLING = 9;
  public static final int MIN_DOWNSAMPLING = 1;
  public static final int MAX_DOWNSAMPLING = 10;

  public static boolean isValidBlurRadius(int value) {
    return value >= MIN_BLUR_RADIUS && value <= MAX_BLUR_RADIUS;
  }

  public static boolean isValidDownsampling(int value) {
    return value >= MIN_DOWNSAMPLING && value <= MAX_DOWNSAMPLING;
  }
}
