package com.goubaa.harry.nightplus.Views.Explores;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by harry on 2018/1/9.
 */

public class GlideImageLoader extends ImageLoader {

  @Override

  public void displayImage(Context context, Object path, ImageView imageView) {
    Picasso.with(context).load(path.toString()).into(imageView);
  }
}
