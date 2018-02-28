package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Library.CustomPicasso;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.SearchPost;
import com.goubaa.harry.nightplus.R;

import java.util.List;

public class ExploresPostViewItemAdapter extends ArrayAdapter<SearchPost> {

  private List<SearchPost> lists;
  private int resourceId;
  private Context context;

  public ExploresPostViewItemAdapter(@NonNull Context context, int resource, @NonNull
    List<SearchPost> lists) {
    super(context, resource, lists);
    this.context = context;
    this.resourceId = resource;
    this.lists = lists;
  }

  @Override
  public int getCount() {
    return lists == null ? 0 : lists.size();
  }

  @Nullable
  @Override
  public SearchPost getItem(int position) {
    return lists.get(position);
  }

  @Override
  public long getItemId(int position) {
    return super.getItemId(position);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    SearchPost searchPost = lists.get(position);
    Post post = searchPost.get_source();
    View view = LayoutInflater.from(context).inflate(resourceId, null);
    String pictureUrl = "https://source.staging.nightplus.cn/f882986da49e446e969e614561602af2";
    List<Post.DefaultComponentsBean> defaultComponentsBeans = post.getDefaultComponents();
    if (defaultComponentsBeans != null) {
      for (int i = 0; i < defaultComponentsBeans.size(); i++) {
        String name = defaultComponentsBeans.get(i).getName();
        if (name == "component-banner") {
          List<String> l = defaultComponentsBeans.get(i).getContent().getBanner();
          pictureUrl = l.get(0);
        }
      }
    }

    TextView title = (TextView) view.findViewById(R.id.explores_list_post_title);
    ImageView picture = (ImageView) view.findViewById(R.id.explores_list_post_picture);

    CustomPicasso.with(context).load(pictureUrl).into(picture);

    title.setText(post.getTitle());

    return view;
  }
}
