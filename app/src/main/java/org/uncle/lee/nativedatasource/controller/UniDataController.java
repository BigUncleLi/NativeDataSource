package org.uncle.lee.nativedatasource.controller;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.uncle.lee.nativedatasource.Entity.in.App;
import org.uncle.lee.nativedatasource.Entity.in.Contact;
import org.uncle.lee.nativedatasource.dao.DaoMaster;

/**
 * Created by Austin on 2016/7/6.
 */
public class UniDataController {
  private static UniDataController dataController;
  private DataController<Contact, String> contactDao;
  private DataController<App, String> appDao;
  private CacheDataController cacheDataController;

  public UniDataController(Context mContext) {
    DaoMaster.DevOpenHelper devOpenHelper =
        new DaoMaster.DevOpenHelper(mContext, DataController.DATABASE_NAME, null);
    ExecutorService threadPool = Executors.newCachedThreadPool();

    appDao = new AppDataController(devOpenHelper, threadPool);
    contactDao = new ContactDataController(devOpenHelper, threadPool);
    cacheDataController = new CacheDataController(threadPool);
  }

  public DataController<Contact, String> contactDataController() {
    return contactDao;
  }

  public DataController<App, String> appDataController() {
    return appDao;
  }

  public CacheDataController cacheDataController() {
    return cacheDataController;
  }
}
