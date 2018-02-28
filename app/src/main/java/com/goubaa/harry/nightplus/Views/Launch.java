package com.goubaa.harry.nightplus.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.SessionApplication;
import com.goubaa.harry.nightplus.Views.BannerActivity.BannerActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Launch extends BaseActivity {

  @BindView(R.id.launch)
  ImageView launchImage;

  private static final int ANIMATION_TIME = 2000;
  private static final float SCALE_END = 1.13F;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences sharedPreferences = SessionApplication.sharedPreferences;
    /**
     * 根据版本号, 获取用户是否用户 引导页面
     */
    String isFirstOpenApp = sharedPreferences.getString("INTRODUCTION", "");
    if (isFirstOpenApp.isEmpty()) {
      startActivity(new Intent(Launch.this, IntroductionActivity.class));
      Launch.this.finish();
      return;
    }
    setContentView(R.layout.activity_launch);
    ButterKnife.bind(this);
    setTranslucentStatus(true);
//    launchImage.setImageResource(R.drawable.welcome);
    Observable.timer(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Consumer<Long>() {
      @Override
      public void accept(Long aLong) throws Exception {
        startAnim();
      }
    });
  }
//
//  @Override
//  public String setActName() {
//    return null;
//  }

  private void startAnim() {
    startActivity(new Intent(Launch.this, BannerActivity.class));
    Launch.this.finish();

//    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//
//    ObjectAnimator animatorX = ObjectAnimator.ofFloat(launchImage, "scaleX", 1f, SCALE_END);
//    ObjectAnimator animatorY = ObjectAnimator.ofFloat(launchImage, "scaleY", 1f, SCALE_END);
//
//    AnimatorSet set = new AnimatorSet();
//    set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
//    set.start();
//
//    set.addListener(new AnimatorListenerAdapter() {
//      @Override
//      public void onAnimationEnd(Animator animation) {
//        startActivity(new Intent(Launch.this, MainActivity.class));
//        Launch.this.finish();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//      }
//    });
  }

}
