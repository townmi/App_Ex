package com.goubaa.harry.nightplus.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.Models.Message;
import com.goubaa.harry.nightplus.R;

import java.util.List;

/**
 * Created by harry on 2018/1/9.
 */

public class ExploresViewItemAdapter extends ArrayAdapter<Message> {

  private List<Message> messages;
//  private LayoutInflater layoutInflater;
  private int resourceId;

  public ExploresViewItemAdapter(Context context, int resId, List<Message> messages) {
    super(context, resId, messages);
    this.messages = messages;
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
