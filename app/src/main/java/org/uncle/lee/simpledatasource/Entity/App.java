package org.uncle.lee.simpledatasource.Entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "APP".
 */
@Entity
public class App {

    @Id
    private Long id;

    @NotNull
    private String packageName;
    private String appLabel;
    private String appPyLabel;
    private String className;

    @Generated
    public App() {
    }

    public App(Long id) {
        this.id = id;
    }

    @Generated
    public App(Long id, String packageName, String appLabel, String appPyLabel, String className) {
        this.id = id;
        this.packageName = packageName;
        this.appLabel = appLabel;
        this.appPyLabel = appPyLabel;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getPackageName() {
        return packageName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPackageName(@NotNull String packageName) {
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

}
