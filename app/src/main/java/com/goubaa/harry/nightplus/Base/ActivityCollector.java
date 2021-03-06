package com.goubaa.harry.nightplus.Base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by harry on 2017/12/6.
 */

public class ActivityCollector {
  private static Stack<Activity> activityStack;
  private static ActivityCollector instance;

  private ActivityCollector() {

  }

  public void refreshAllActivity() {
    for (Activity activity : activityStack) {
      activity.recreate();
    }
  }

  /**
   * 单一实例
   */
  public static ActivityCollector getInstance() {
    if (instance == null) {
      instance = new ActivityCollector();
    }
    return instance;
  }

  /**
   * 添加Activity到堆栈
   */
  public void addActivity(Activity activity) {
    if (activityStack == null) {
      activityStack = new Stack<Activity>();
    }
    activityStack.add(activity);
  }


  public void removeActivity(Activity activity) {
    activityStack.remove(activity);
  }


  /**
   * 获取当前Activity（堆栈中最后一个压入的）
   */
  public Activity currentActivity() {
    Activity activity = activityStack.lastElement();
    return activity;
  }

  /**
   * 结束当前Activity（堆栈中最后一个压入的）
   */
  public void finishActivity() {
    Activity activity = activityStack.lastElement();
    finishActivity(activity);
  }

  /**
   * 结束指定的Activity
   */
  public void finishActivity(Activity activity) {
    if (activity != null && !activity.isFinishing()) {
      activityStack.remove(activity);
      activity.finish();
      activity = null;
    }
  }

  /**
   * 结束指定类名的Activity
   */
  public void finishActivity(Class<?> cls) {
    for (Activity activity : activityStack) {
      if (activity.getClass().equals(cls)) {
        finishActivity(activity);
        break;
      }
    }
  }

  /**
   * 结束所有Activity
   */
  public void finishAllActivity() {
    for (int i = 0, size = activityStack.size(); i < size; i++) {
      if (null != activityStack.get(i)) {
        finishActivity(activityStack.get(i));
        break;
      }
    }
    activityStack.clear();
  }

  /**
   * 获取指定的Activity
   */
  public static Activity getActivity(Class<?> cls) {
    if (activityStack != null)
      for (Activity activity : activityStack) {
        if (activity.getClass().equals(cls)) {
          return activity;
        }
      }
    return null;
  }

  /**
   * 退出应用程序
   */
  public void AppExit(Context context) {
    try {
      finishAllActivity();
      // 杀死该应用进程
      android.os.Process.killProcess(android.os.Process.myPid());
      System.exit(0);
    } catch (Exception e) {
    }
  }
}
