package org.uncle.lee.simpledatasource.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Entity.out.UniApp;
import org.uncle.lee.simpledatasource.Entity.out.UniContact;
import org.uncle.lee.simpledatasource.Utils.LogUtils;
import org.uncle.lee.simpledatasource.Utils.Transformer;

/**
 * Created by Austin on 2016/7/7.
 */
public class CacheDataController {
  private ExecutorService threadPool;
  private List<App> inputAppList;
  private List<Contact> inputContact;

  private List<UniApp> outputAppList;
  private List<UniContact> outputContactList;

  public CacheDataController(ExecutorService threadPool) {
    this.threadPool = threadPool;
  }

  public void saveCacheAppList(List<App> apps, SaveType SaveType) {
    initOutputAppListFirstTime();

    if(SaveType.equals(CacheDataController.SaveType.TYPE_ADD)){
      addIntoCacheAppList(apps);
    }else if(SaveType.equals(CacheDataController.SaveType.TYPE_CLEAN)){
      cleanCacheAppList();
    }
  }

  private List<App> appsTemp;
  private void addIntoCacheAppList(List<App> apps) {
    this.appsTemp = apps;
    Future<List<UniApp>> future = threadPool.submit(cacheAppCallable);
    try {
      if(future.isDone()){
        outputAppList = future.get();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void cleanCacheAppList() {
    outputAppList.clear();
  }

  // only init first time
  private void initOutputAppListFirstTime() {
    if(outputAppList == null){
      outputAppList = new ArrayList<UniApp>();
    }
  }

  private Callable<List<UniApp>> cacheAppCallable = new Callable<List<UniApp>>() {
    @Override public List<UniApp> call() throws Exception {
      outputAppList = Transformer.transformUniApp(outputAppList, appsTemp);
      return outputAppList;
    }
  };

  public void saveCacheContactList(List<Contact> contacts) {
    outputContactList = Transformer.transformUniContact(contacts);
  }

  public List<UniApp> cacheAppList(){
    return outputAppList;
  }

  public List<UniContact> cacheContactList(){
    return outputContactList;
  }

  public enum SaveType{
    TYPE_ADD, TYPE_CLEAN;
  }
}
