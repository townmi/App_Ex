package com.goubaa.harry.nightplus.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;


import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Launch extends BaseActivity {

  @BindView(R.id.launch)
  ImageView launchImage;

  private static final int ANIMATION_TIME = 2000;
  private static final float SCALE_END = 1.13F;

  private static final int[] IMAGES = {
    R.drawable.launch_default,
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch);
    ButterKnife.bind(this);
    setTranslucentStatus(true);
//        Random random = new Random(SystemClock.elapsedRealtime());
//        launchImage.setImageResource(IMAGES[random.nextInt(IMAGES.length)]);
    launchImage.setImageResource(IMAGES[0]);
//        Log.i("x", "" + random.nextInt(IMAGES.length));

    Observable.timer(400, TimeUnit.MILLISECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Consumer<Long>() {
        @Override
        public void accept(Long aLong) throws Exception {
          startAnim();
        }
      });
  }

//  @Override
//  public String setActName() {
//    return null;
//  }

  private void startAnim() {

    ObjectAnimator animatorX = ObjectAnimator.ofFloat(launchImage, "scaleX", 1f, SCALE_END);
    ObjectAnimator animatorY = ObjectAnimator.ofFloat(launchImage, "scaleY", 1f, SCALE_END);

    AnimatorSet set = new AnimatorSet();
    set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
    set.start();

    set.addListener(new AnimatorListenerAdapter() {

      @Override
      public void onAnimationEnd(Animator animation) {

        startActivity(new Intent(Launch.this, MainActivity.class));
        Launch.this.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
      }
    });
  }

}
