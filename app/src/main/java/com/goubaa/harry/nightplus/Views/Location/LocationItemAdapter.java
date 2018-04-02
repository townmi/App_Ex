package com.goubaa.harry.nightplus.Views.Location;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.goubaa.harry.nightplus.Library.CustomPicasso;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.R;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class LocationItemAdapter extends ArrayAdapter {

  private ArrayList<City> lists;
  private int resourceId;
  private Context context;

  public LocationItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList lists) {
    super(context, resource, lists);
    this.context = context;
    this.resourceId = resource;
    this.lists = lists;
  }

  @Override
  public int getCount() {
    return lists == null ? 0 : lists.size();
  }

  @Nullable
  @Override
  public Object getItem(int position) {
    return lists.get(position);
  }

  @Override
  public long getItemId(int position) {
    return super.getItemId(position);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = LayoutInflater.from(context).inflate(resourceId, null);
    City city = lists.get(position);

    String cityCover = city.getCover();
    LogUtil.info(cityCover);
    ImageView picture = (ImageView) view.findViewById(R.id.location_item_picture);
    CustomPicasso.with(context).load(cityCover).tag("desiredWidth").transform(new MatchTransformation(context, picture)).into(picture);

    return view;
  }


  private static class MatchTransformation implements Transformation {
    RenderScript rs;
    ImageView view;
    int width;

    public MatchTransformation(Context context, ImageView view) {
      super();
      rs = RenderScript.create(context);
      this.view = view;
      this.width = context.getResources().getDimensionPixelSize(R.dimen.x300);
    }

    @Override
    public Bitmap transform(Bitmap source) {

      int targetWidth = width;
      if (source.getWidth() == 0) {
        return source;
      }

      // 如果图片尺寸 不够，拉大处理
      double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
      int targetHeight = (int) (targetWidth * aspectRatio);
      if (targetHeight != 0 && targetWidth != 0) {
        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
        if (result != source) {
          source.recycle();
        }
        return result;
      } else {
        return source;
      }
    }

    @Override
    public String key() {
      return "desiredWidth";
    }
  }
}
