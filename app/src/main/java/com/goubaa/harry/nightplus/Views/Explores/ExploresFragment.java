package com.goubaa.harry.nightplus.Views.Explores;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goubaa.harry.nightplus.Base.BaseFragment;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Adapter.ExploresViewPagerAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploresFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
  @BindView(R.id.fragment_explores_tab_layout)
  TabLayout tabLayout;

  @BindView(R.id.fragment_explores_layout)
  CoordinatorLayout coordinatorLayout;

  @BindView(R.id.fragment_explores)
  ViewPager viewPager;

  @BindView(R.id.explores_banner)
  Banner banner;


  private ExploresViewPagerAdapter exploresViewPagerAdapter;
  private String[] mTitles;


  private OnFragmentInteractionListener mListener;

  public ExploresFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_explores, container, false);
    ButterKnife.bind(this, view);

    List<String> images = new ArrayList<>();

    images.add("http://ooa2erl8d.bkt.clouddn.com/fb06efc070004037a0e5061869ad7187.jpg");
    images.add("http://ooa2erl8d.bkt.clouddn.com/88032e3511b941a89a5fda390ae7a15e.jpg");

    banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
    return view;
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters
   *
   * @return A new instance of fragment ExploresFragment.
   */
  public static ExploresFragment newInstance() {
    ExploresFragment fragment = new ExploresFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initViews();
  }

  private void initViews() {
    String[] s = {
      "自选", "BNB", "BTC", "ETH", "USDT"
    };
    mTitles = s;
    exploresViewPagerAdapter = new ExploresViewPagerAdapter(getChildFragmentManager(), mTitles);
    viewPager.setAdapter(exploresViewPagerAdapter);

    viewPager.addOnPageChangeListener(this);

    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.setTabMode(TabLayout.MODE_FIXED);

    SharedPreferences pref = getContext().getSharedPreferences("ThemeColor", getContext().MODE_PRIVATE);
    tabLayout.setSelectedTabIndicatorColor(pref.getInt("themeColor", Color.rgb(232, 179, 66)));

    tabLayout.setTabTextColors(getResources().getColor(R.color.colorLightGray), pref.getInt("themeColor", Color.rgb(232, 179, 66)));
    tabLayout.setupWithViewPager(viewPager);


    viewPager.setOffscreenPageLimit(1);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
        + " must implement OnFragmentInteractionListener");
    }
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);

//        public void onArticleSelected(int position);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onDetach() {
    super.onDetach();
    banner.stopAutoPlay();
  }

  @Override
  public boolean hasMultiFragment() {
    return false;
  }

  @Override
  protected String setFragmentName() {
    return "动态";
  }

}
