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
import android.renderscript.RenderScript;
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
import com.squareup.picasso.Transformation;

import java.util.List;

public class ExploresPostViewItemAdapter extends ArrayAdapter<SearchPost> {

  private List<SearchPost> lists;
  private int resourceId;
  private Context context;
  private int radius;

  public ExploresPostViewItemAdapter(@NonNull Context context, int resource, @NonNull
    List<SearchPost> lists) {
    super(context, resource, lists);
    this.context = context;
    this.resourceId = resource;
    this.radius = context.getResources().getDimensionPixelSize(R.dimen.x7);
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
    Post.PostedByBean postedBy = post.getPostedBy();
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
    ImageView authorPicture = (ImageView) view.findViewById(R.id
      .explores_list_post_author_picture);
    TextView authorName = (TextView) view.findViewById(R.id.explores_list_post_author_name);

    CustomPicasso.with(context).load(pictureUrl).transform(new BlurTransformation(context, radius)).into
      (picture);
    CustomPicasso.with(context).load(postedBy.getHeadImgUrl()).into(authorPicture);

    title.setText(post.getTitle());
    authorName.setText(postedBy.getDisplayName());

    return view;
  }

  /**
   *
   */
  private static class BlurTransformation implements Transformation {
    RenderScript rs;
    int radius;

    public BlurTransformation(Context context, int radius) {
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
      canvas.drawRect(rectF.right - radius, rectF.bottom - radius, rectF.right, rectF.bottom, paint);

      paint.setFilterBitmap(true);
      paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      canvas.drawBitmap(source, 0, 0, paint);
      source.recycle();//释放

      return bitmap;
    }


    @Override
    public String key() {
      return "gray";
    }
  }
}
