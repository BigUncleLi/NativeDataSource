package org.uncle.lee.simpledatasource.entity;

/**
 * Created by Austin on 2016/7/6.
 */
public class App {
  private String packageName;
  private String appLabel;
  private String appPyLabel;
  private String className;

  public App() {
  }

  public App(String packageName, String appLabel, String appPyLabel, String className) {
    this.packageName = packageName;
    this.appLabel = appLabel;
    this.appPyLabel = appPyLabel;
    this.className = className;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getAppLabel() {
    return appLabel;
  }

  public void setAppLabel(String appLabel) {
    this.appLabel = appLabel;
  }

  public String getAppPyLabel() {
    return appPyLabel;
  }

  public void setAppPyLabel(String appPyLabel) {
    this.appPyLabel = appPyLabel;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override public String toString() {
    return "App{" +
        "packageName='" + packageName + '\'' +
        ", appLabel='" + appLabel + '\'' +
        ", appPyLabel='" + appPyLabel + '\'' +
        ", className='" + className + '\'' +
        '}';
  }
}
