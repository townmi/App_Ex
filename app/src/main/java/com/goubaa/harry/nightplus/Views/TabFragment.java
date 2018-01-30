package com.goubaa.harry.nightplus.Views;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Components.SearchBar;
import com.goubaa.harry.nightplus.R;

/**
 * Created by harry on 2017/12/7.
 */

public class TabFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
    if (container == null) {
      return null;
    }

    String mTitle = "发现";
    if (getArguments() != null) {
      mTitle = getArguments().getString("Title", "发现");
      if (mTitle == "我的") {
        return null;
//                SearchBar searchBar = (SearchBar) getFragmentManager().findFragmentById(R.id.main_viewpager);
      } else {
        return defaultRender(mTitle);
      }
    } else {

      return defaultRender(mTitle);
    }
  }

  @Override
  public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);

  }

  private View defaultRender(String title) {
    ScrollView scroller = new ScrollView(getActivity());
    TextView text = new TextView(getActivity());
    int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
      4, getActivity().getResources().getDisplayMetrics());
    text.setPadding(padding, padding, padding, padding);
    scroller.addView(text);
    text.setText(title);
    return scroller;
  }
}
