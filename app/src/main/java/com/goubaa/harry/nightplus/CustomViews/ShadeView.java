package com.goubaa.harry.nightplus.CustomViews;

import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * ShadeView tab View
 *
 * @author HarryTang
 */
public class ShadeView extends View {

  private Bitmap iconBitmap;
  private Bitmap DEFAULT_ICON_BITMAP;
  private Bitmap DEFAULT_ICON_BITMAP_ACTIVE;

  private int iconBackgroundColor;
  private final int DEFAULT_ICON_BACKGROUND_COLOR = Color.parseColor("#ffffff");

  private String text;
  private final int DEFAULT_TEXT_SIZE = 12;

  private final int DEFAULT_TEXT_COLOR = Color.parseColor("#999999");
  private final int DEFAULT_TEXT_COLOR_ACTIVE = Color.parseColor("#46c01b");

  private Rect iconRect;
  private Paint textPaint;
  private Rect textBound;
  private float mAlpha = 1.0f;
  private Bitmap mBitmap;
  private int textColor;

  public ShadeView(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadeView);

    BitmapDrawable drawable = (BitmapDrawable) typedArray.getDrawable(R.styleable.ShadeView_icon);

    BitmapDrawable drawableActive = (BitmapDrawable) typedArray.getDrawable(R.styleable.ShadeView_icon_active);

    if (drawable != null) {
      iconBitmap = drawable.getBitmap();
      DEFAULT_ICON_BITMAP = drawable.getBitmap();
      DEFAULT_ICON_BITMAP_ACTIVE = drawableActive.getBitmap();
    }

    iconBackgroundColor = typedArray.getColor(R.styleable.ShadeView_color, DEFAULT_ICON_BACKGROUND_COLOR);
    text = typedArray.getString(R.styleable.ShadeView_text);
    textColor = DEFAULT_TEXT_COLOR;

    int textSize = (int) typedArray.getDimension(R.styleable.ShadeView_text_size, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
      DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));

    typedArray.recycle();

    // 初始化
    textBound = new Rect();
    textPaint = new Paint();
    textPaint.setTextSize(textSize);
    textPaint.setColor(textColor);
    textPaint.setAntiAlias(true);
    textPaint.setDither(true);
    textPaint.getTextBounds(text, 0, text.length(), textBound);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int bitmapSide = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom()
      - textBound.height());
    int left = getMeasuredWidth() / 2 - bitmapSide / 2;
    int top = (getMeasuredHeight() - textBound.height()) / 2 - bitmapSide / 2;
    iconRect = new Rect(left, top, left + bitmapSide, top + bitmapSide);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    int alpha = (int) Math.ceil((255 * this.mAlpha));
    canvas.drawBitmap(iconBitmap, null, iconRect, null);
    setupTargetBitmap(alpha);
    drawTargetText(canvas, alpha);
    canvas.drawBitmap(mBitmap, 0, 0, null);
  }

  private void setupTargetBitmap(int alpha) {
    mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(mBitmap);
    Paint paint = new Paint();
    iconBackgroundColor = alpha < 20 ? DEFAULT_TEXT_COLOR : DEFAULT_TEXT_COLOR_ACTIVE;
    paint.setColor(iconBackgroundColor);
    paint.setAntiAlias(true);
    paint.setDither(true);
//    paint.setAlpha(255 - alpha);
    paint.setAlpha(Math.abs(alpha - 125) + 125);
    canvas.drawRect(iconRect, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    canvas.drawBitmap(iconBitmap, null, iconRect, paint);
  }

  private void drawTargetText(Canvas canvas, int alpha) {
    textColor = alpha < 20 ? DEFAULT_TEXT_COLOR : DEFAULT_TEXT_COLOR_ACTIVE;
    textPaint.setColor(textColor);
    int _alpha = Math.abs(alpha - 125) + 200 > 255 ? 255 : Math.abs(alpha - 125) + 200;
    textPaint.setAlpha(_alpha);
    canvas.drawText(text, iconRect.left + iconRect.width() / 2 - textBound.width() / 2, iconRect.bottom + textBound.height(), textPaint);
  }


  public void setIconAlpha(float alpha) {
    if (mAlpha != alpha) {
      this.mAlpha = alpha;
      this.iconBitmap = DEFAULT_ICON_BITMAP_ACTIVE;
      if (alpha < 0.5) {
        this.iconBitmap = DEFAULT_ICON_BITMAP;
      }
      invalidateView();
    }
  }

  private void invalidateView() {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      invalidate();
    } else {
      postInvalidate();
    }
  }

  private static final String STATE_INSTANCE = "STATE_INSTANCE";
  private static final String STATE_ALPHA = "STATE_ALPHA";

  @Override
  protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
    bundle.putFloat(STATE_ALPHA, mAlpha);
    return bundle;
  }


  @Override
  protected void onRestoreInstanceState(Parcelable parcelable) {
    if (parcelable instanceof Bundle) {
      Bundle bundle = (Bundle) parcelable;
      mAlpha = bundle.getFloat(STATE_ALPHA);
      super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
    } else {
      super.onRestoreInstanceState(parcelable);
    }
  }

}
