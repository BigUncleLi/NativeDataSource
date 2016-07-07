package org.uncle.lee.simpledatasource.data.center;

import android.content.Context;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.controller.pub.UniDataController;
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

  private UniDataCenter(Context mContext){
    uniDataController = new UniDataController(mContext);
  }

  public static UniDataCenter getInstance(Context mContext){
    if(uniDataCenter == null){
      synchronized (uniDataCenter.getClass()){
        if(uniDataCenter == null){
          uniDataCenter = new UniDataCenter(mContext);
        }
      }
    }
    return uniDataCenter;
  }

  @Override public void getContactList() {
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

  @Override public void setContactList(List<Contact> contactList) {
    cleanContactAndInsert(contactList);
  }

  private void cleanContactAndInsert(List<Contact> contactList) {
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          startInsertContactList(contacts);
        }
      }
    });
    uniDataController.contactDao().clean();
  }

  private void startInsertContactList(List<Contact> contacts) {
    uniDataController.contactDao().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.INSERT_DONE, true, contacts);
          saveCacheContactList(contacts);
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
        }
      }
    });
    uniDataController.contactDao().clean();
  }

  @Override public void getAppList() {
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

  @Override public void setAppList(List<App> appList) {
    cleanAppAndInsert(appList);
  }

  private void cleanAppAndInsert(List<App> appList) {
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          startInsertAppList(apps);
        }
      }
    });
    uniDataController.appDao().clean();
  }

  private void startInsertAppList(List<App> apps) {
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.INSERT_DONE, true, apps);
          saveCacheAppList(apps);
        }
      }
    });
    uniDataController.appDao().insert(apps);
  }

  @Override public void cleanAppList() {
    uniDataController.appDao().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        UniDataCenter.this.listener.onAction(UniDataCenterListener.ActionType.CLEAN_DONE, true, null);
      }
    });
    uniDataController.appDao().clean();
  }

  @Override public void setListener(UniDataCenterListener listener) {
    this.listener = listener;
  }
}
