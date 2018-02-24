/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;
import android.os.Bundle;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.CustomViews.CanvasDraw;
import com.goubaa.harry.nightplus.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class BannerActivity extends BaseActivity {
  private int alpha;
  @BindView(R.id.explores_banner)
  Banner banner;


  @BindView(R.id.mission_canvas)
  CanvasDraw canvasDraw;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_banner);
    List<String> images = new ArrayList<>();

    images.add("http://ooa2erl8d.bkt.clouddn.com/fb06efc070004037a0e5061869ad7187.jpg");
    images.add("http://ooa2erl8d.bkt.clouddn.com/88032e3511b941a89a5fda390ae7a15e.jpg");

    banner.setImages(images).setImageLoader(new GlideImageLoader()).start();


    Timer timer = new Timer();
    alpha = 255;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        alpha--;
        if (alpha < 0) {
          alpha = 255;
          cancel();
        }
        canvasDraw.refresh(alpha);
      }
    }, 100, 40);
  }
}
