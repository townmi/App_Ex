package com.goubaa.harry.nightplus.Library;

import android.os.Environment;
import android.util.Log;

import com.goubaa.harry.nightplus.SessionApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogUtil class
 *
 * @author HarryTang
 */
public class LogUtil {
  private static final String TAG = "LogUtil";
  private static final String basePath = Environment.getExternalStorageDirectory()
    .getAbsolutePath() + File.separator + "log" + File.separator;

  private static String fileName = null;
  private static SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat TimeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void debug(String msg) {
    String logString = createMessage(msg);
    if (SessionApplication.canWriteStorage || (!SessionApplication.canWriteStorage &&
      checkWritePermission())) {
      writeLog2SDCard("DEBUG", logString);
    }
    Log.d(TAG, logString);
  }

  public static void info(String msg) {
    String logString = createMessage(msg);
    if (SessionApplication.canWriteStorage || (!SessionApplication.canWriteStorage &&
      checkWritePermission())) {
      writeLog2SDCard("INFO*", logString);
    }
    Log.i(TAG, logString);
  }

  public static void error(String msg) {
    String logString = createMessage(msg);
    if (SessionApplication.canWriteStorage || (!SessionApplication.canWriteStorage &&
      checkWritePermission())) {
      writeLog2SDCard("ERROR", logString);
    }
    Log.e(TAG, logString);
  }

  /**
   * @param msg
   * @return
   */
  private static String createMessage(String msg) {
    StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
    String fullClassName = stackTraceElement.getClassName();
    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    return className + "." + stackTraceElement.getMethodName() + "(): " + msg;
  }

  /**
   * writeLog2SDCard
   *
   * @param type
   * @param context
   */
  private static void writeLog2SDCard(String type, String context) {
    fileName = (fileName == null) ? logDateFormat.format(new Date(System.currentTimeMillis())) +
      ".txt" : fileName;
    File file = new File(basePath + fileName);
    String dateString = TimeDateFormat.format(new Date(System.currentTimeMillis()));
    context = dateString + "==" + type + "==" + context;
    try {
      if (!file.exists()) {
        file.getParentFile().mkdir();
        file.createNewFile();
      }
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new
        FileOutputStream(file, true)));
      bufferedWriter.write(context);
      bufferedWriter.newLine();
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * checkWritePermission
   *
   * @return
   */
  private static boolean checkWritePermission() {
    boolean canWriteStorage = true;
    fileName = (fileName == null) ? logDateFormat.format(new Date(System.currentTimeMillis())) +
      ".txt" : fileName;
    File file = new File(basePath + fileName);
    try {
      if (!file.exists()) {
        file.getParentFile().mkdir();
        file.createNewFile();
        canWriteStorage = true;
      }
    } catch (Exception e) {
      canWriteStorage = false;
    }
    SessionApplication.setCanWriteStorage(canWriteStorage);
    return canWriteStorage;
  }
}
