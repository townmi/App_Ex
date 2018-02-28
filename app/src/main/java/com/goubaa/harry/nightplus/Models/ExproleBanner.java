package com.goubaa.harry.nightplus.Models;

import java.util.List;

public class ExproleBanner {
  /**
   * view : {"count":2,"rows":[]}
   */
  private ViewBean<CmsView> view;

  public ViewBean<CmsView> getView() {
    return view;
  }

  public void setView(ViewBean<CmsView> view) {
    this.view = view;
  }

  public static class ViewBean<CmsView> {
    /**
     * count : 2
     * rows : []
     */
    private int count;
    private List<CmsView> rows;

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public List<CmsView> getRows() {
      return rows;
    }

    public void setRows(List<CmsView> rows) {
      this.rows = rows;
    }
  }

}