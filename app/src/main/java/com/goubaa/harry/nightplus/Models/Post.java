package com.goubaa.harry.nightplus.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by harry on 2018/1/26.
 */

public class Post {
  String _id;
  int postType;

  Object message;

  int shareCount;
  int commentCount;
  int favoriteCount;
  int likeCount;

  Date createdAt;
  Date modifiedAt;

  //  ArrayList<T> affiliates;
//  ArrayList informers;
  boolean isDeleted;
  boolean isRecommend;

  String likeId;
  String favoriteId;

//  ArrayList location;


  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }
}
