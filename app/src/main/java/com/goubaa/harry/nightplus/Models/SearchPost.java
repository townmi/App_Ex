package com.goubaa.harry.nightplus.Models;

import java.util.List;

public class SearchPost {

  /**
   * _index : cmspost
   * _type : cmspost
   * _id : 5a88f1a7fcf02c39d1e0fc4d
   * _score : null
   * _source : {}
   * sort : [1518924199380]
   */

  private String _index;
  private String _type;
  private String _id;
  private Object _score;
  private Post _source;
  private List<Long> sort;

  public String get_index() {
    return _index;
  }

  public void set_index(String _index) {
    this._index = _index;
  }

  public String get_type() {
    return _type;
  }

  public void set_type(String _type) {
    this._type = _type;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public Object get_score() {
    return _score;
  }

  public void set_score(Object _score) {
    this._score = _score;
  }

  public Post get_source() {
    return _source;
  }

  public void set_source(Post _source) {
    this._source = _source;
  }

  public List<Long> getSort() {
    return sort;
  }

  public void setSort(List<Long> sort) {
    this.sort = sort;
  }

  public static class SourceBean {
  }
}
