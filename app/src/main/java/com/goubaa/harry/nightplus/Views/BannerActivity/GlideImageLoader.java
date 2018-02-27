/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.widget.ImageView;

import com.goubaa.harry.nightplus.R;
import com.squareup.picasso.Picasso;
import com.goubaa.harry.banner.loader.ImageLoader;

/**
 * Created by harry on 2018/1/9.
 */

public class GlideImageLoader extends ImageLoader {

  @Override
  public void displayImage(Context context, Object path, ImageView imageView) {
    int x = context.getResources().getDimensionPixelSize(R.dimen.x320);
    Picasso.with(context).load(path.toString()).resize(x, x).centerCrop().into(imageView);
  }
}
