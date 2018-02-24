package com.goubaa.harry.nightplus.Views.Explores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.goubaa.harry.nightplus.Adapter.ExploresViewItemAdapter;
import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseFragment;
import com.goubaa.harry.nightplus.Base.BaseObserver;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.Message;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.Me.MeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploresFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

  private MeFragment.OnFragmentInteractionListener mListener;

  @BindView(R.id.explores_list)
  ListView listView;

  public ExploresFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_explores, container, false);
    ButterKnife.bind(this, view);
    listView.setDivider(null);
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
    try {
      ArrayList<Message> arrayList = new ArrayList<Message>();
      arrayList.add(new Message("吃鸡群", "本来是:", R.drawable.a1i));
      arrayList.add(new Message("订阅号", "苏州交警:", R.drawable.a1j));
      arrayList.add(new Message("上海公积金", "喜迎春节:", R.drawable.a1k));
      arrayList.add(new Message("NIGHT+ tech core team", "繁星海风:", R.drawable.a1l));
      arrayList.add(new Message("腾讯新闻", "女子喝醉:", R.drawable.a1m));
      arrayList.add(new Message("肯德基", "情人节倒计时:", R.drawable.a1n));
      if (arrayList != null) {
        ExploresViewItemAdapter exploresViewItemAdapter = new ExploresViewItemAdapter(getContext(), R.layout.item_view_explores, arrayList);
        listView.setAdapter(exploresViewItemAdapter);
      }
    } catch (NullPointerException e) {
      LogUtil.error(e.getMessage());
    }
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
    if (context instanceof MeFragment.OnFragmentInteractionListener) {
      mListener = (MeFragment.OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement " + "OnFragmentInteractionListener");
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
  }

  @Override
  public boolean hasMultiFragment() {
    return false;
  }

  @Override
  protected String setFragmentName() {
    return "动态";
  }


  private void getStudents() {

    Observable<BaseEntity<City>> observable = RetrofitFactory.getInstance().getCities();
    observable.compose(compose(this.<BaseEntity<City>>bindToLifecycle())).subscribe(new BaseObserver<City>(getContext()) {
      @Override
      protected void onHandleSuccess(ArrayList<City> arrayList) {
        City city;
        String _id = "";
      }
    });
  }

}
