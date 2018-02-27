/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Models;

import java.util.ArrayList;

/**
 * Created by harry on 2018/2/27.
 */

public class GraphqlRows<T> {
  private int count;
  private ArrayList<T> rows;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public ArrayList<T> getRows() {
    return rows;
  }

  public void setRows(ArrayList<T> rows) {
    this.rows = rows;
  }
}
