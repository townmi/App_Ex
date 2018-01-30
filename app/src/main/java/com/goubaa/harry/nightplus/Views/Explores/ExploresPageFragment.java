package com.goubaa.harry.nightplus.Views.Explores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.goubaa.harry.nightplus.Adapter.ExploresViewItemAdapter;
import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploresPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploresPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploresPageFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private RecyclerView.LayoutManager layoutManager;
  private ExploresViewItemAdapter adapter;

//    @BindView(R.id.explores_list_wrap)
//    ListView listView;

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  public ExploresPageFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ExploresPageFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ExploresPageFragment newInstance(String param1, String param2) {
    ExploresPageFragment fragment = new ExploresPageFragment();
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
    layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_explores_page, container, false);
    ButterKnife.bind(this, view);

//        List<Student> studentList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Student stu = new Student();
//
//            stu.setAge(i + 10);
//            stu.setName("xxx" + i);
//            stu.setPhoto(1000 * i);
//            studentList.add(stu);
//        }
//
//        adapter = new ExploresViewItemAdapter(this.getContext(), R.layout.item_view_explores, studentList);
//
//        listView.setAdapter(adapter);

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
      throw new RuntimeException(context.toString()
        + " must implement OnFragmentInteractionListener");
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
}
