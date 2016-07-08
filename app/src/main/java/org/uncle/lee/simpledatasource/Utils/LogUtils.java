package org.uncle.lee.simpledatasource.Utils;

import android.util.Log;

/**
 * Created by Austin on 2016/7/8.
 */
public class LogUtils {
  private static final boolean IS_DEBUG = true;

  public static void d(String TAG, String content){
    if(IS_DEBUG){
      Log.d(TAG, content);
    }
  }
}
