package org.uncle.lee.simpledatasource.data.center;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Utils.LogUtils;
import org.uncle.lee.simpledatasource.Utils.Transformer;
import org.uncle.lee.simpledatasource.controller.CacheDataController;
import org.uncle.lee.simpledatasource.controller.UniDataController;
import org.uncle.lee.simpledatasource.listener.ActionType;
import org.uncle.lee.simpledatasource.listener.DataControllerListener;
import org.uncle.lee.simpledatasource.listener.DataCenterListener;

/**
 * Created by Austin on 2016/7/7.
 */

// using thread to calculate
public class UniDataCenter implements DataCenter {
  public static final String TAG = UniDataCenter.class.getSimpleName();
  private static UniDataCenter uniDataCenter;
  private DataCenterListener listener;
  private UniDataController uniDataController;
  private Context mContext;

  private UniDataCenter(Context mContext){
    this.mContext = mContext;
    uniDataController = new UniDataController(mContext);
  }


  @Override
  public void init(Context mContext) {
    if(listener == null){
      throw new RuntimeException("DataCenterListener is null !");
    }

    if(uniDataCenter == null){
      synchronized (UniDataCenter.class){
        if(uniDataCenter == null){
          uniDataCenter = new UniDataCenter(mContext);
        }
      }
    }
    if(listener != null){
      listener.onAction(ActionType.INIT_DONE, null);
    }
  }

  public static UniDataCenter getInstance(Context mContext){
    if(uniDataCenter == null){
      throw new RuntimeException("UniDataCenter must init first !");
    }else {
      return uniDataCenter;
    }
  }

  @Override public void queryContactList() {
    if(!isHasContactCacheData()){
      queryContactInFirstTime();
    }else {
      this.listener.onAction(ActionType.QUERY_ALL_DONE,
              uniDataController.cacheDataController().cacheContactList());
    }
  }

  private boolean isHasContactCacheData() {
    return uniDataController.cacheDataController().cacheContactList() != null;
  }

  private void queryContactInFirstTime() {
    uniDataController.contactDataController().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.QUERY_ALL_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(ActionType.QUERY_ALL_DONE, contacts);
          saveCacheContactList(contacts, CacheDataController.SaveType.TYPE_ADD);
        }
      }
    });
    uniDataController.contactDataController().queryAll();
  }

  @Override public void insertContactList(List<Contact> contactList) {
    contactList = new Transformer().addPyForContact(mContext, contactList);
    final List<Contact> finalContactList = contactList;
    uniDataController.contactDataController().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(ActionType.INSERT_DONE, null);
          saveCacheContactList(finalContactList, CacheDataController.SaveType.TYPE_ADD);
        }
      }
    });
    uniDataController.contactDataController().insert(contactList);
  }

  private void saveCacheContactList(List<Contact> contacts, CacheDataController.SaveType saveType) {
    LogUtils.d(TAG, saveType + " cache contact list ...");
    uniDataController.cacheDataController().saveCacheContactList(contacts, saveType);
  }

  @Override public void cleanContactList() {
    uniDataController.contactDataController().setListener(new DataControllerListener<Contact>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<Contact> contacts) {
        if(Type.equals(ActionType.CLEAN_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(ActionType.CLEAN_DONE, null);
          saveCacheContactList(Collections.<Contact>emptyList(), CacheDataController.SaveType.TYPE_CLEAN);
        }
      }
    });
    uniDataController.contactDataController().clean();
  }

  @Override public void queryAppList() {
    if(!isHasAppCacheData()){
      queryAppInFirstTime();
    }else {
      this.listener.onAction(ActionType.QUERY_ALL_DONE,
          uniDataController.cacheDataController().cacheAppList());
    }
  }

  private boolean isHasAppCacheData(){
    return uniDataController.cacheDataController().cacheAppList() != null;
  }

  private void queryAppInFirstTime() {
    uniDataController.appDataController().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.QUERY_ALL_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(ActionType.QUERY_ALL_DONE, apps);
          saveCacheAppList(apps, CacheDataController.SaveType.TYPE_ADD);
        }
      }
    });
    uniDataController.appDataController().queryAll();
  }

  private void saveCacheAppList(List<App> apps, CacheDataController.SaveType saveType) {
    LogUtils.d(TAG, "start cache app list ...");
    uniDataController.cacheDataController().saveCacheAppList(apps, saveType);
  }

  @Override public void insertAppList(final List<App> appList) {
    // app don't need to get py params(this part is too wasting time)
    uniDataController.appDataController().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        if(Type.equals(ActionType.INSERT_DONE) && isSuccess){
          UniDataCenter.this.listener.onAction(ActionType.INSERT_DONE, null);
          saveCacheAppList(appList, CacheDataController.SaveType.TYPE_ADD);
        }
      }
    });
    uniDataController.appDataController().insert(appList);
  }

  @Override public void cleanAppList() {
    uniDataController.appDataController().setListener(new DataControllerListener<App>() {
      @Override public void onAction(ActionType Type, boolean isSuccess, List<App> apps) {
        UniDataCenter.this.listener.onAction(ActionType.CLEAN_DONE, null);
        saveCacheAppList(Collections.<App>emptyList(), CacheDataController.SaveType.TYPE_CLEAN);
      }
    });
    uniDataController.appDataController().clean();
  }

  @Override public void setListener(DataCenterListener listener) {
    this.listener = listener;
  }
}
