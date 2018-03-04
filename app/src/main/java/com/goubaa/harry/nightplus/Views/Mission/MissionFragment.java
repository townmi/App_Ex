package com.goubaa.harry.nightplus.Views.Mission;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goubaa.harry.nightplus.Base.BaseEntity;
import com.goubaa.harry.nightplus.Base.BaseFragment;
import com.goubaa.harry.nightplus.Base.BaseObserver;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.CustomViews.CanvasDraw;
import com.goubaa.harry.nightplus.SessionApplication;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MissionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissionFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;


  private OnFragmentInteractionListener mListener;

  public MissionFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MissionFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MissionFragment newInstance(String param1, String param2) {
    MissionFragment fragment = new MissionFragment();
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
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
    savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_mission, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
      getPost("5a658edebc02c25463dd425f");
    } else {
      throw new RuntimeException(context.toString() + " must implement " +
        "OnFragmentInteractionListener");
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
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public boolean hasMultiFragment() {
    return false;
  }

  @Override
  protected String setFragmentName() {
    return "动态";
  }

  private void getPost(String id) {

    Observable<BaseEntity<Post>> observable = RetrofitFactory.getCommunityCoreRetrofitService()
      .getPostInfo(id);
    observable.compose(compose(this.<BaseEntity<Post>>bindToLifecycle())).subscribe(new BaseObserver<Post>(getContext()) {
      @Override
      protected void onHandleSuccess(ArrayList<Post> arrayList) {
        Post post;
        String _id = "";
        try {
          if (arrayList != null && arrayList.size() > 0) {
            post = arrayList.get(0);
            _id = post.get_id();
            LogUtil.info(_id);
            SessionApplication.getElectricity();
          }
        } catch (NullPointerException e) {
          LogUtil.error(e.getMessage());
        }
//        new AlertDialog.Builder(getContext())
//          .setTitle("AJAX Success")
//          .setMessage("userId: " + _id)
//          .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//          })
//          .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//          }).create().show();
      }
    });

  }
}
