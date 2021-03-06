package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseFragment;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Base.BaseObserver;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.internal.observers.BlockingBaseObserver;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploresFollowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploresFollowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploresFollowFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  @BindView(R.id.explores_follow_list)
  ListView listView;

  private OnFragmentInteractionListener mListener;

  public ExploresFollowFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ExploresFollowFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ExploresFollowFragment newInstance(String param1, String param2) {
    ExploresFollowFragment fragment = new ExploresFollowFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_explores_follow, container, false);
    ButterKnife.bind(this, view);

    Resources resources = getResources();
    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    int statusBarHeight = resources.getDimensionPixelSize(resourceId);
    int height = statusBarHeight + resources.getDimensionPixelSize(R.dimen.x36);
    listView.setPadding(0, height, 0, 0);
    getPosts();
    return view;
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
      throw new RuntimeException(context.toString() + " must implement " + "OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
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
  public boolean hasMultiFragment() {
    return false;
  }

  @Override
  protected String setFragmentName() {
    return null;
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

  private void getPosts() {
    Observable<BaseEntity<Post>> observable = RetrofitFactory.getCommunityCoreRetrofitService().getPosts("-createdAt", 10, 0, "[0,1]", true, "", false);

    observable.compose(compose(this.<BaseEntity<Post>>bindToLifecycle())).subscribe(new BaseObserver<Post>(getContext()) {
      @Override
      protected void onHandleSuccess(ArrayList<Post> arrayList) {
        try {

          ExploresFollowPostViewItemAdapter exploresFollowPostViewItemAdapter = new ExploresFollowPostViewItemAdapter(getContext(), R.layout.activity_banner_follow_post_info, arrayList);
          listView.setAdapter(exploresFollowPostViewItemAdapter);
        } catch (Exception e) {
          LogUtil.error(e.getMessage());
        }
      }

      @Override
      public void onError(Throwable e) {
        super.onError(e);
      }

      @Override
      protected void onHandleError(String msg) {
        super.onHandleError(msg);
      }
    });
  }
}
