package com.goubaa.harry.nightplus.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goubaa.harry.nightplus.Models.City;
import com.goubaa.harry.nightplus.R;

import java.util.List;

/**
 * Created by harry on 2018/1/9.
 */

public class ExploresViewItemAdapter extends BaseAdapter {

  private List<City> studentList;
  private LayoutInflater layoutInflater;
  private int resourceId;

  public ExploresViewItemAdapter(Context context, int resId, List<City> studentList) {
    this.studentList = studentList;
    this.resourceId = resId;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return studentList == null ? 0 : studentList.size();
  }

  @Override
  public City getItem(int position) {
    return studentList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Log.i("SSSS", "" + resourceId);

    View view = layoutInflater.inflate(resourceId, null);
    TextView nameView = (TextView) view.findViewById(R.id.explores_list_item_name);
//        TextView ageView = (TextView) view.findViewById(R.id.explores_list_item_age);

    City student = getItem(position);

    nameView.setText(student.get_id());
//        ageView.setText(student.getAge());

    return view;
  }
}
