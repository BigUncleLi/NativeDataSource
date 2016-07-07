package org.uncle.lee.simpledatasource.data.center;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.controller.UniDataController;
import org.uncle.lee.simpledatasource.listener.DataControllerListener;
import org.uncle.lee.simpledatasource.listener.UniDataCenterListener;

/**
 * Created by Austin on 2016/7/7.
 */
public class UniDataCenter implements DataCenter {
  private static UniDataCenter uniDataCenter;
  private UniDataCenterListener listener;
  private UniDataController uniDataController;
  private List<Contact> cacheContactList;
  private List<App> cacheAppList;
  private List<Contact> contactTemp;
  private List<App> appTemp;

  private UniDataCenter(Context mContext){
    uniDataController = new UniDataController(mContext);
  }

  public static UniDataCenter getInstance(Context mContext){
    if(uniDataCenter == null){
      synchronized (UniDataCenter.class){
        if(uniDataCenter == null){
          uniDataCenter = new UniDataCenter(mContext);
        }
      }
    }
    return uniDataCenter;
  }

  @Override public void queryContactList() {
    if(cacheContactList ==  null){
      queryContactInFirstTime();
    }else {
      this.listener.onAction(UniDataCenterListener.ActionType.QUERY_ALL_DONE, true, cacheContactList);
    }
  }

  private void queryContactInFirstTime() {
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.QUERY_ALL_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.QUERY_ALL_DONE, true, contacts);
          saveCacheContactList(contacts);
        }
      }
    });
    uniDataController.contactDao().queryAll();
  }

  @Override public void insertContactList(List<Contact> contactList) {
    cleanContactAndInsert(contactList);
  }

  private void cleanContactAndInsert(List<Contact> contactList) {
    this.contactTemp = contactList;
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          startInsertContactList(contactTemp);
        }
      }
    });
    uniDataController.contactDao().clean();
  }

  private void startInsertContactList(List<Contact> contacts) {
    this.contactTemp = contacts;
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.INSERT_DONE, true, null);
          saveCacheContactList(contactTemp);
        }
      }
    });
    uniDataController.contactDao().insert(contacts);
  }

  private void saveCacheContactList(List<Contact> contacts) {
    this.cacheContactList = contacts;
  }

  @Override public void cleanContactList() {
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.CLEAN_DONE, true, null);
          saveCacheContactList(Collections.<Contact>emptyList());
        }
      }
    });
    uniDataController.contactDao().clean();
  }

  @Override public void queryAppList() {
    if(cacheAppList == null){
      queryAppInFirstTime();
    }else {
      this.listener.onAction(UniDataCenterListener.ActionType.QUERY_ALL_DONE, true, cacheAppList);
    }
  }

  private void queryAppInFirstTime() {
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.QUERY_ALL_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.QUERY_ALL_DONE, true, apps);
          saveCacheAppList(apps);
        }
      }
    });
    uniDataController.appDao().queryAll();
  }

  private void saveCacheAppList(List<App> apps) {
    this.cacheAppList = apps;
  }

  @Override public void insertAppList(List<App> appList) {
    cleanAppAndInsert(appList);
  }

  private void cleanAppAndInsert(List<App> appList) {
    this.appTemp = appList;
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          startInsertAppList(appTemp);
        }
      }
    });
    uniDataController.appDao().clean();
  }

  private void startInsertAppList(List<App> apps) {
    this.appTemp = apps;
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.INSERT_DONE, true, null);
          saveCacheAppList(appTemp);
        }
      }
    });
    uniDataController.appDao().insert(apps);
  }

  @Override public void cleanAppList() {
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.CLEAN_DONE, true, null);
        saveCacheAppList(Collections.<App>emptyList());
      }
    });
    uniDataController.appDao().clean();
  }

  @Override public void setListener(UniDataCenterListener listener) {
    this.listener = listener;
  }
}
