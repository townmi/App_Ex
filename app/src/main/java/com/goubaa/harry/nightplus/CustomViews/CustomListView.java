package com.goubaa.harry.nightplus.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by harry on 2018/2/28.
 */

public class CustomListView extends ListView {

  public CustomListView(Context context) {
    super(context);
  }

  public CustomListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  /**
   * 重写该方法，达到使ListView适应ScrollView的效果
   */ protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
