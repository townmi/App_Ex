package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.goubaa.harry.nightplus.Library.CustomPicasso;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.SearchPost;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.BuyerPostInfo.BuyerPostInfo;
import com.goubaa.harry.nightplus.Views.MainActivity;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import co.lujun.androidtagview.TagContainerLayout;

public class ExploresPostViewItemAdapter extends ArrayAdapter<SearchPost> {

  private List<SearchPost> lists;
  private int resourceId;
  private Context context;
  private int radius;
  private int authorPictureWidth;

  public ExploresPostViewItemAdapter(@NonNull Context context, int resource, @NonNull
    List<SearchPost> lists) {
    super(context, resource, lists);
    this.context = context;
    this.resourceId = resource;
    this.radius = context.getResources().getDimensionPixelSize(R.dimen.x5);
    this.authorPictureWidth = context.getResources().getDimensionPixelSize(R.dimen.x48);
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
    View view = LayoutInflater.from(context).inflate(resourceId, null);

    SearchPost searchPost = lists.get(position);
    // post type
    String _index = searchPost.get_index();
    // post model data
    Post post = searchPost.get_source();
    String _id = post.get_id();

    String pictureUrl = "https://source.staging.nightplus.cn/f882986da49e446e969e614561602af2";
    String _authorName = "NIGHT+";
    String _authorDescription = "~ ~";
    String _authorDescribe = "this men has nothing to show";
    String _authorPicture = "https://h5.nightplus.cn/app/e2595977b76939c4ac3042e8f4da26c4.png";
    ArrayList<String> _tags = new ArrayList<>();

    Post.PostedByBean postedBy = post.getPostedBy();

    List<Post.DefaultComponentsBean> defaultComponentsBeans = post.getDefaultComponents();
    if (defaultComponentsBeans != null) {
      for (int i = 0; i < defaultComponentsBeans.size(); i++) {
        String name = defaultComponentsBeans.get(i).getName();
        if (name.equals("component-banner")) {
          if (defaultComponentsBeans.get(i).getContent() != null) {
            if (_index.equals("cmspost")) {
              ArrayList<LinkedTreeMap<String, String>> l = (ArrayList<LinkedTreeMap<String,
                String>>) defaultComponentsBeans.get(i).getContent();
              pictureUrl = l.get(0).get("imgUrl");
            } else if (_index.equals("communitypost")) {
              LinkedTreeMap<String, ArrayList<String>> l = (LinkedTreeMap<String,
                ArrayList<String>>) defaultComponentsBeans.get(i).getContent();
              if (l.get("banner") != null && l.get("banner").get(0) != null) {
                pictureUrl = l.get("banner").get(0);
              }
            }
          }
        }
      }
    }

    if (postedBy != null) {
      if (postedBy.getDisplayName() != null) {
        _authorName = postedBy.getDisplayName();
      }
      if (postedBy.getHeadImgUrl() != null) {
        _authorPicture = postedBy.getHeadImgUrl();
      }
      if (postedBy.getTitle() != null) {
        _authorDescription = postedBy.getTitle();
      }
      if (postedBy.getDescribe() != null) {
        _authorDescribe = postedBy.getDescribe();
      }
    }

    TextView title = (TextView) view.findViewById(R.id.explores_list_post_title);
    ImageView picture = (ImageView) view.findViewById(R.id.explores_list_post_picture);
    ImageView authorPicture = (ImageView) view.findViewById(R.id.explores_list_post_author_picture);
    TextView authorName = (TextView) view.findViewById(R.id.explores_list_post_author_name);
    TextView authorDescription = (TextView) view.findViewById(R.id
      .explores_list_post_author_description);
    TextView authorDescribe = (TextView) view.findViewById(R.id.explores_list_post_author_describe);
    TagContainerLayout tags = (TagContainerLayout) view.findViewById(R.id.explores_list_post_tag);
    Button followBtn = (Button) view.findViewById(R.id.explores_list_post_author_follow);

    CustomPicasso.with(context).load(pictureUrl).tag("smallRadius").transform(new
      SmallRadiusTransformation(context, radius)).into(picture);
    CustomPicasso.with(context).load(_authorPicture).tag("radius").resize(authorPictureWidth,
      authorPictureWidth).centerCrop().transform(new RadiusTransformation(context)).into
      (authorPicture);

    title.setText(post.getTitle());
    authorName.setText(_authorName);
    authorDescription.setText(_authorDescription);
    authorDescribe.setText(_authorDescribe);

    _tags.add("shift");
    _tags.add("测试");
    tags.setTags(_tags);

    followBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
      }
    });

    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, BuyerPostInfo.class);
        intent.putExtra("post", post);
        context.startActivity(intent);
      }
    });

    return view;
  }

  /**
   *
   */
  private static class SmallRadiusTransformation implements Transformation {
    RenderScript rs;
    int radius;

    public SmallRadiusTransformation(Context context, int radius) {
      super();
      rs = RenderScript.create(context);
      this.radius = radius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
      int width = source.getWidth();
      int height = source.getHeight();
      //画板
      Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
      Paint paint = new Paint();
      Canvas canvas = new Canvas(bitmap);//创建同尺寸的画布
      paint.setAntiAlias(true);//画笔抗锯齿
      paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
      //画圆角背景
      RectF rectF = new RectF(new Rect(0, 0, width, height));//赋值
      canvas.drawRoundRect(rectF, radius, radius, paint);//画圆角矩形

      canvas.drawRect(0, rectF.bottom - radius, radius, rectF.bottom, paint);
      canvas.drawRect(rectF.right - radius, rectF.bottom - radius, rectF.right, rectF.bottom,
        paint);

      paint.setFilterBitmap(true);
      paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      canvas.drawBitmap(source, 0, 0, paint);
      source.recycle();//释放

      return bitmap;
    }


    @Override
    public String key() {
      return "smallRadius";
    }
  }


  private static class RadiusTransformation implements Transformation {

    public RadiusTransformation(Context context) {
      super();
    }

    @Override
    public Bitmap transform(Bitmap source) {
      int width = source.getWidth();
      int height = source.getHeight();
      //画板
      Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
      Paint paint = new Paint();
      Canvas canvas = new Canvas(bitmap);//创建同尺寸的画布
      paint.setAntiAlias(true);//画笔抗锯齿
      paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
      //画圆角背景
      RectF rectF = new RectF(new Rect(0, 0, width, height));//赋值
      canvas.drawRoundRect(rectF, width / 2, height / 2, paint);//画圆角矩形

      paint.setFilterBitmap(true);
      paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      canvas.drawBitmap(source, 0, 0, paint);
      source.recycle();//释放

      return bitmap;
    }


    @Override
    public String key() {
      return "radius";
    }

  }
}
