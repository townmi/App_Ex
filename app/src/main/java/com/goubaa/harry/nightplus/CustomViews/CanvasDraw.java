package com.goubaa.harry.nightplus.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.goubaa.harry.nightplus.R;

/**
 * Created by harry on 2018/1/9.
 */

public class CanvasDraw extends View {

  private Paint paint;
  private Rect rect;

  private final int DEFAULT_TEXT_SIZE = 14;
  private String text;

  public CanvasDraw(Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
    setWillNotDraw(false);

    TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CanvasDraw);
    text = typedArray.getString(R.styleable.CanvasDraw_canvas_text);

    int textSize = (int) typedArray.getDimension(R.styleable.CanvasDraw_canvas_text_size, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
    typedArray.recycle();

    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(textSize);

    rect = new Rect();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    try {

      paint.setColor(Color.RED);
      canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

      Log.i("ssss", text);

      float textWidth = rect.width();
      float textHeight = rect.height();
      canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, paint);

    } catch (Exception ex) {
      Log.i("ssss", ex.toString());
    }

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int bitmapSide = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - rect.height());
    int left = getMeasuredWidth() / 2 - bitmapSide / 2;
    int top = (getMeasuredHeight() - rect.height()) / 2 - bitmapSide / 2;
    rect = new Rect(left, top, left + bitmapSide, top + bitmapSide);
  }

  private void invalidateView() {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      invalidate();
    } else {
      postInvalidate();
    }
  }

  public void refresh() {
    text = "what ";
    invalidateView();
  }

  private static final String STATE_INSTANCE = "STATE_INSTANCE";

  @Override
  protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
    return bundle;
  }


  @Override
  protected void onRestoreInstanceState(Parcelable parcelable) {
    if (parcelable instanceof Bundle) {
      Bundle bundle = (Bundle) parcelable;
      super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
    } else {
      super.onRestoreInstanceState(parcelable);
    }
  }
}
