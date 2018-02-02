package com.goubaa.harry.nightplus.Models;

/**
 * Created by harry on 2018/2/2.
 */

class Wechat {
  String city;
  String country;
  String headimgurl;
  String id;
  String nickName;
  String province;
  String sex;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getHeadimgurl() {
    return headimgurl;
  }

  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}


public class User {
  String id;
  String WechatId;
  Wechat wechat;
  String cityId;
  String describe;
  String displayName;
  String headimgurl;
  boolean isPolicy;
  int level;
  String mobile;
  int subscribe;
  String tags;
  String title;


  public Wechat getWechat() {
    return wechat;
  }

  public void setWechat(Wechat wechat) {
    this.wechat = wechat;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWechatId() {
    return WechatId;
  }

  public void setWechatId(String wechatId) {
    WechatId = wechatId;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getDescribe() {
    return describe;
  }

  public void setDescribe(String describe) {
    this.describe = describe;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getHeadimgurl() {
    return headimgurl;
  }

  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }

  public boolean isPolicy() {
    return isPolicy;
  }

  public void setPolicy(boolean policy) {
    isPolicy = policy;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public int getSubscribe() {
    return subscribe;
  }

  public void setSubscribe(int subscribe) {
    this.subscribe = subscribe;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
