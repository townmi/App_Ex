package com.goubaa.harry.nightplus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Models.Message;
import com.goubaa.harry.nightplus.R;
import com.goubaa.harry.nightplus.Views.BannerActivity.BannerActivity;

import java.util.List;

/**
 * ExploresViewItemAdapter
 *
 * @author HarryTang
 */
public class ExploresViewItemAdapter extends ArrayAdapter<Message> {

  private List<Message> messages;
  //  private LayoutInflater layoutInflater;
  private int resourceId;
  private Context context;

  public ExploresViewItemAdapter(Context context, int resId, List<Message> messages) {
    super(context, resId, messages);
    this.messages = messages;
    this.context = context;
    this.resourceId = resId;
//    this.layoutInflater = LayoutInflater.from(context);
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Message message = getItem(position);  //获取当前项的Fruit实例
    //为子项动态加载布局
    View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
    ImageView messagePicture = (ImageView) view.findViewById(R.id.explores_list_image);
    TextView messageTitle = (TextView) view.findViewById(R.id.explores_list_item_title);
    TextView messageDescription = (TextView) view.findViewById(R.id.explores_list_item_description);
    messageTitle.setText(message.getTitle());
    messageDescription.setText(message.getDescription());
    messagePicture.setImageResource(message.getImage());

    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        context.startActivity(new Intent(context, BannerActivity.class));
      }
    });

    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        PopupMenu mPopupMenu = new PopupMenu(getContext(), v);
        mPopupMenu.inflate(R.menu.menu_main_explores_item_normal);
        mPopupMenu.show();
        return false;
      }
    });


    return view;
  }


  @Override
  public int getCount() {
    return messages == null ? 0 : messages.size();
  }

  @Override
  public Message getItem(int position) {
    return messages.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

}
