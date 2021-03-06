package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.goubaa.harry.banner.Banner;
import com.goubaa.harry.banner.BannerConfig;
import com.goubaa.harry.nightplus.Base.BaseEntityObject;
import com.goubaa.harry.nightplus.Base.BaseFragment;
import com.goubaa.harry.nightplus.Base.BaseObserverObject;
import com.goubaa.harry.nightplus.Base.RetrofitFactory;
import com.goubaa.harry.nightplus.CustomViews.BlurScrollView.NotifyingScrollView;
import com.goubaa.harry.nightplus.CustomViews.BlurScrollView.app.GlassActionBarHelper;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Library.Utils;
import com.goubaa.harry.nightplus.Models.CmsView;
import com.goubaa.harry.nightplus.Models.ExproleBanner;
import com.goubaa.harry.nightplus.Models.ExprolePosts;
import com.goubaa.harry.nightplus.Models.SearchPost;
import com.goubaa.harry.nightplus.R;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploresChoiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploresChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploresChoiceFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private List<String> images = new ArrayList<>();
  private List<String> titles = new ArrayList<>();
  private int height;

  @BindView(R.id.explores_follow_body)
  ScrollView body;

  @BindView(R.id.explores_banner)
  Banner banner;

  @BindView(R.id.explores_list)
  ListView listView;

  private OnFragmentInteractionListener mListener;
  private GlassActionBarHelper blurHelper;
  private static int width;

  public ExploresChoiceFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ExploresChoiceFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ExploresChoiceFragment newInstance(String param1, String param2) {
    ExploresChoiceFragment fragment = new ExploresChoiceFragment();
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

    View view = inflater.inflate(R.layout.fragment_explores_choice, container, false);
//    Resources resources = getResources();
//    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//    int statusBarHeight = resources.getDimensionPixelSize(resourceId);
//
//    height = statusBarHeight + resources.getDimensionPixelSize(R.dimen.x36);
//    width = resources.getDimensionPixelSize(R.dimen.x320);
//    int screenHeight = resources.getDimensionPixelSize(R.dimen.y160);

//    blurHelper = new GlassActionBarHelper().contentLayout(R.layout.fragment_explores_choice, height);
//    View view = blurHelper.createView(getContext());

    ButterKnife.bind(this, view);

//    body.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {
//      @Override
//      public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
//        if (t > banner.getHeight() && t % screenHeight == 1) {
//          blurHelper.invalidate();
//        }
//      }
//    });

    /**
     * fetch banners
     */
    String query = "{view(isDisplayed:true,sectionType:\"community-banner\"," + "cityIds:[\"58d1ecade841a18ba5399026\"]){count," + "rows{_id,title,viewType,url,image, " + "articleId, " + "subTitle," +
      "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "topic{id," + "topicName}}}}";
    getBanners(query);
    getExplorePosts("58d1ecade841a18ba5399026");
    return view;

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
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onDetach() {
    super.onDetach();
//    blurHelper.setStop(true);
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
    return null;
  }

  private void getBanners(String query) {
    Observable<BaseEntityObject<ExproleBanner>> observable = RetrofitFactory.getCmsRetorfitService().getBanners(query);
    observable.compose(compose(this.<BaseEntityObject<ExproleBanner>>bindToLifecycle())).subscribe(new BaseObserverObject<ExproleBanner>(getContext()) {
      @Override
      protected void onHandleSuccess(ExproleBanner exproleBanner) {
        ExproleBanner.ViewBean<CmsView> view = exproleBanner.getView();
        List<CmsView> bannerList = view.getRows();
        for (int i = 0; i < bannerList.size(); i++) {
          CmsView banner = bannerList.get(i);
          images.add(banner.getImage());
          titles.add(banner.getTitle());
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.setImageLoader(new GlideImageLoader()).start();
//        blurHelper.invalidate();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            if (positionOffsetPixels % 2 == 1) {
//              blurHelper.invalidate();
//            }
          }

          @Override
          public void onPageSelected(int position) {
          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
        });

      }
    });
  }

  private void getExplorePosts(String cityId) {
    Observable<BaseEntityObject<ExprolePosts>> observable = RetrofitFactory.getExplorePostsRetorfitService().getExplorePosts("-createdAt", 10, 0, cityId, true);
    observable.compose(compose(this.<BaseEntityObject<ExprolePosts>>bindToLifecycle())).subscribe(new BaseObserverObject<ExprolePosts>(getContext()) {
      @Override
      protected void onHandleSuccess(ExprolePosts exprolePosts) {
        try {
          List<SearchPost> list = exprolePosts.getHits();
          if (list != null) {
            ExploresPostViewItemAdapter exploresPostViewItemAdapter = new ExploresPostViewItemAdapter(getContext(), R.layout.activity_banner_post, list);
            listView.setAdapter(exploresPostViewItemAdapter);
            Utils.setListViewHeightBasedOnChildren(listView);
          }
        } catch (NullPointerException e) {
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
