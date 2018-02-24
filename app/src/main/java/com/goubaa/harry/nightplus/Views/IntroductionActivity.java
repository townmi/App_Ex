package com.goubaa.harry.nightplus.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.goubaa.harry.nightplus.Adapter.IntroductionViewPagerAdatper;
import com.goubaa.harry.nightplus.Base.BaseActivity;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.SessionApplication;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionActivity extends BaseActivity {
  private Button button;

  @BindView(R.id.introduction)
  ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_introduction);
    ButterKnife.bind(this);

    ArrayList arrayList = new ArrayList<View>();
    LayoutInflater layoutInflater = getLayoutInflater().from(IntroductionActivity.this);
    View viewFirst = layoutInflater.inflate(R.layout.activity_introduction_inflater_first, null);
    View viewSecond = layoutInflater.inflate(R.layout.activity_introduction_inflater_second, null);
    View viewThird = layoutInflater.inflate(R.layout.activity_introduction_inflater_third, null);

    arrayList.add(viewFirst);
    arrayList.add(viewSecond);
    arrayList.add(viewThird);

    viewPager.setAdapter(new IntroductionViewPagerAdatper(arrayList));

    button = viewThird.findViewById(R.id.introduction_btn);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences.Editor editor = SessionApplication.sharedPreferences.edit();
        // 写入已读过介绍
        editor.putString("INTRODUCTION", "read");
        editor.commit();

        startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
        IntroductionActivity.this.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
      }
    });
  }
}
