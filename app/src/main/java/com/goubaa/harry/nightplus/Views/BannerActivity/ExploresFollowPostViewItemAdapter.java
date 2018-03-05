package com.goubaa.harry.nightplus.Views.BannerActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Library.CustomPicasso;
import com.goubaa.harry.nightplus.Library.LogUtil;
import com.goubaa.harry.nightplus.Models.Post;
import com.goubaa.harry.nightplus.Models.SearchPost;
import com.goubaa.harry.nightplus.R;
import com.squareup.picasso.Transformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExploresFollowPostViewItemAdapter extends ArrayAdapter {
  private List<Post> lists;
  private Typeface typeface;
  private int resourceId;
  private Context context;
  private int radius;
  private int authorPictureWidth;
  private int postPictureWidth;
  private int getPostPictureMargin;

  public ExploresFollowPostViewItemAdapter(@NonNull Context context, int resource, @NonNull List
    objects) {
    super(context, resource, objects);

    this.context = context;
    this.resourceId = resource;
    this.radius = context.getResources().getDimensionPixelSize(R.dimen.x5);
    this.authorPictureWidth = context.getResources().getDimensionPixelSize(R.dimen.x48);
    this.postPictureWidth = context.getResources().getDimensionPixelSize(R.dimen.x84);
    this.getPostPictureMargin = context.getResources().getDimensionPixelSize(R.dimen.x9);
    this.lists = objects;

    this.typeface = Typeface.createFromAsset(context.getResources().getAssets(), "ionicons.ttf");
  }

  @Override
  public int getCount() {
    return lists == null ? 0 : lists.size();
  }


  @Nullable
  @Override
  public Post getItem(int position) {
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
    ImageButton authorPicture = (ImageButton) view.findViewById(R.id
      .explores_list_follow_post_author_picture);
    TextView authorName = (TextView) view.findViewById(R.id.explores_list_follow_post_author_name);
    TextView authorPostDate = (TextView) view.findViewById(R.id.explores_list_follow_post_date);
    TextView postDescription = (TextView) view.findViewById(R.id
      .explores_list_follow_post_description);
    LinearLayout postPictures = (LinearLayout) view.findViewById(R.id
      .explores_list_follow_post_pictures);
    TextView followBtn = (TextView) view.findViewById(R.id.explores_list_follow_post_author_follow);

    followBtn.setTypeface(typeface);

    Post post = lists.get(position);
    Post.PostedByBean postedBy = post.getPostedBy();
    int postType = post.getPostType();
    Post.MessageBean message = post.getMessage();
    List<Post.DefaultComponentsBean> defaultComponentsBean = post.getDefaultComponents();
    String _authorPicture = "";
    String _authorName = "";
    String _authorPostDate = "";
    String _postDescription = "";
    ArrayList<String> _postPictures = new ArrayList<>();

    if (postedBy != null) {
      if (postedBy.getDisplayName() != null) {
        _authorPicture = postedBy.getHeadImgUrl();
      }
      if (postedBy.getHeadImgUrl() != null) {
        _authorName = postedBy.getDisplayName();
      }
    }
    if (postType == 0 && message != null) {
      _postDescription = message.getDescription();
      _postPictures = (ArrayList<String>) message.getImages();
    }

    if (_postPictures != null) {
      if (_postPictures.size() == 1) {
        // 如果图片只有一张，就自适应加载
        ImageView _view = new ImageView(context);
        _view.setAdjustViewBounds(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
          .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        _view.setLayoutParams(layoutParams);
        CustomPicasso.with(context).load(_postPictures.get(0)).tag("radius").into(_view);
        postPictures.addView(_view);
      } else {
        // 如果多个图片，就九宫格加载
        for (int i = 0; i < _postPictures.size(); i++) {
          ImageView _view = new ImageView(context);
          int _i = i % 3;
          LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this
            .postPictureWidth, this.postPictureWidth);
          if (_i == 1) {
            LogUtil.debug("" + this.getPostPictureMargin);
            layoutParams.setMargins(this.getPostPictureMargin, 0, this.getPostPictureMargin, 0);
          }
          _view.setLayoutParams(layoutParams);
          CustomPicasso.with(context).load(_postPictures.get(i)).tag("radius").fit().centerCrop()
            .into(_view);
          postPictures.addView(_view);
        }
      }
    }

    if (defaultComponentsBean != null) {
      for (int i = 0; i < defaultComponentsBean.size(); i++) {
        if (defaultComponentsBean.get(i) != null) {
          String name = defaultComponentsBean.get(i).getName();
          if (name != null && name.equals("component-banner") && postType == 0) {

          }
        }
      }
    }

    if (post.getCreatedAt() != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      try {
        Date date = sdf.parse(post.getCreatedAt());
        SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        _authorPostDate = _sdf.format(date);
      } catch (ParseException e) {
        LogUtil.error(e.getMessage());
      }
    }
    postDescription.setText(_postDescription);
    authorName.setText(_authorName);
    authorPostDate.setText(_authorPostDate);
    CustomPicasso.with(context).load(_authorPicture).tag("radius").resize(authorPictureWidth,
      authorPictureWidth).centerCrop().transform(new ExploresFollowPostViewItemAdapter
      .RadiusTransformation(context)).into(authorPicture);

    return view;
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
