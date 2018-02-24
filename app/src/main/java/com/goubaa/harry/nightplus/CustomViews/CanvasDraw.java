package com.goubaa.harry.nightplus.CustomViews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.R;

/**
 * CanvasDraw
 *
 * @author HarryTang
 */
public class CanvasDraw extends View {

  private Paint paint;
  private Rect rect;
  private int alpha;

  private final int DEFAULT_TEXT_SIZE = 14;
  private final int DEFAULT_COLOR = Color.parseColor("#45c01a");
  private String text;

  public CanvasDraw(Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
    setWillNotDraw(false);

    TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CanvasDraw);
    text = typedArray.getString(R.styleable.CanvasDraw_canvas_text);

    int textSize = (int) typedArray.getDimension(R.styleable.CanvasDraw_canvas_text_size, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
      DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));

    typedArray.recycle();

    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(textSize);

    rect = new Rect();
    alpha = 255;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int bitmapSide = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom()
      - rect.height());
    int left = getMeasuredWidth() / 2 - bitmapSide / 2;
    int top = (getMeasuredHeight() - rect.height()) / 2 - bitmapSide / 2;
    LogUtil.debug("" + getMeasuredWidth() + "/" + left + "/" + top + "/" + bitmapSide);
    rect = new Rect(0, 0, left + bitmapSide, top + bitmapSide);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    try {
//      paint.setColor(Color.RED);
      Resources res = this.getResources();
      Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.main_tab_explore);
//      canvas.drawBitmap(bitmap, null, rect, null);

      Bitmap bitmap1 = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);

      Canvas canvas1 = new Canvas(bitmap1);
      Paint paint1 = new Paint();
      paint1.setColor(DEFAULT_COLOR);
      paint1.setAntiAlias(true);
      paint1.setDither(true);
      paint1.setAlpha(Math.abs(alpha - 125)+125);
      canvas.drawRect(rect, paint1);
      paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
      canvas.drawBitmap(bitmap, null, rect, paint);


//      canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
//      float textWidth = rect.width();
//      float textHeight = rect.height();
//      canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2,
//        paint);

    } catch (Exception e) {
      LogUtil.error(e.getMessage());
    }
  }

  private void invalidateView() {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      invalidate();
    } else {
      postInvalidate();
    }
  }

  public void refresh(int alpha) {
    text = "what ";
    this.alpha = alpha;
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
