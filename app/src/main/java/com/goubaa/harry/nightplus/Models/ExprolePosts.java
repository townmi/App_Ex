package com.goubaa.harry.nightplus.Models;

import java.util.List;

public class ExprolePosts {

  /**
   * total : 92
   * max_score : null
   * hits : []
   */

  private int total;
  private Object max_score;
  private List<SearchPost> hits;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public Object getMax_score() {
    return max_score;
  }

  public void setMax_score(Object max_score) {
    this.max_score = max_score;
  }

  public List<SearchPost> getHits() {
    return hits;
  }

  public void setHits(List<SearchPost> hits) {
    this.hits = hits;
  }
}
