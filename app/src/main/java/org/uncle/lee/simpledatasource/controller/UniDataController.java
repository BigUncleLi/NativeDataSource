package org.uncle.lee.simpledatasource.controller;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.dao.DaoMaster;

/**
 * Created by Austin on 2016/7/6.
 */
public class UniDataController {
  private static UniDataController dataController;
  private DataController<Contact, String> contactDao;
  private DataController<App, String> appDao;

  public UniDataController(Context mContext){
    DaoMaster.DevOpenHelper devOpenHelper =
        new DaoMaster.DevOpenHelper(mContext, DataController.DATABASE_NAME, null);
    ExecutorService threadPool = Executors.newCachedThreadPool();

    appDao = new AppDataController(devOpenHelper, threadPool);
    contactDao = new ContactDataController(devOpenHelper, threadPool);
  }

  public DataController<Contact, String> contactDao() {
    return contactDao;
  }

  public DataController<App, String> appDao() {
    return appDao;
  }
}
