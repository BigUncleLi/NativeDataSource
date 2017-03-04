package org.uncle.lee.nativedatasource.Entity.out;

import java.util.ArrayList;

/**
 * Created by Austin on 2016/7/7.
 */
public class UniApp {
  private String mPackageName;
  private String mAppLabel;
  private String mAppLabelPinYin;

  public ArrayList<String> nickNameList;

  public UniApp() {
  }

  public UniApp(String mPackageName, String mAppLabel, String mAppLabelPinYin) {
    this.mPackageName = mPackageName;
    this.mAppLabel = mAppLabel;
    this.mAppLabelPinYin = mAppLabelPinYin;
  }

  public String getmPackageName() {
    return mPackageName;
  }

  public void setmPackageName(String mPackageName) {
    this.mPackageName = mPackageName;
  }

  public String getmAppLabel() {
    return mAppLabel;
  }

  public void setmAppLabel(String mAppLabel) {
    this.mAppLabel = mAppLabel;
  }

  public String getmAppLabelPinYin() {
    return mAppLabelPinYin;
  }

  public void setmAppLabelPinYin(String mAppLabelPinYin) {
    this.mAppLabelPinYin = mAppLabelPinYin;
  }
}
