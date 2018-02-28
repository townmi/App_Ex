/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Models;

public class CmsView {

  /**
   * _id : 5a7bff49294a0f6fa599f7c7
   * title : 春节假期夜晚玩乐大比拼
   * viewType : null
   * url : https://www.baidu.com
   * image : http://ooa2erl8d.bkt.clouddn.com/f2ba339b3dc64ae6bc28484a120bf80a.jpg
   * articleId : null
   * subTitle : null
   * topic : {"id":"5a7bfb2d563f7727e4b29eba","topicName":"春节假期夜晚玩乐大比拼"}
   */

  private String _id;
  private String title;
  private Object viewType;
  private String url;
  private String image;
  private Object articleId;
  private Object subTitle;
  private TopicBean topic;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Object getViewType() {
    return viewType;
  }

  public void setViewType(Object viewType) {
    this.viewType = viewType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Object getArticleId() {
    return articleId;
  }

  public void setArticleId(Object articleId) {
    this.articleId = articleId;
  }

  public Object getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(Object subTitle) {
    this.subTitle = subTitle;
  }

  public TopicBean getTopic() {
    return topic;
  }

  public void setTopic(TopicBean topic) {
    this.topic = topic;
  }

  public static class TopicBean {
    /**
     * id : 5a7bfb2d563f7727e4b29eba
     * topicName : 春节假期夜晚玩乐大比拼
     */

    private String id;
    private String topicName;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTopicName() {
      return topicName;
    }

    public void setTopicName(String topicName) {
      this.topicName = topicName;
    }
  }
}
