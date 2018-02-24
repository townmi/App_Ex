package com.goubaa.harry.nightplus.Models;

/**
 * Created by harry on 2018/1/9.
 */
public class City {

  private String cover;
  private String name;
  private String phone_code;
  private String _id;

  public City(String cover, String name) {
    this.cover = cover;
    this.name = name;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone_code() {
    return phone_code;
  }

  public void setPhone_code(String phone_code) {
    this.phone_code = phone_code;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }
}
