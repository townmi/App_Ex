package com.goubaa.harry.nightplus.Models;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by harry on 2018/2/12.
 */

public class Message {

  /**
   * title : 吃鸡群
   * description : 本来是:
   * time : 晚上8:32
   * image :
   */
  private String title;
  private String description;
  private String time;
  private int image;

  public Message(String title, String description, int image) {
    this.title = title;
    this.description = description;
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }
}