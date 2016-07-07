package org.uncle.lee.simpledatasource.controller.pub;

import android.content.Context;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.controller.AppDataController;
import org.uncle.lee.simpledatasource.controller.ContactDataController;
import org.uncle.lee.simpledatasource.controller.DataController;
import org.uncle.lee.simpledatasource.dao.DaoMaster;

/**
 * Created by Austin on 2016/7/6.
 */
public class UniDataController {
  private static UniDataController dataController;
  private DataController<Contact, String> contactDao;
  private DataController<App, String> appDao;

  private UniDataController(Context mContext){
    DaoMaster.DevOpenHelper devOpenHelper =
        new DaoMaster.DevOpenHelper(mContext, DataController.DATABASE_NAME, null);
    appDao = new AppDataController(devOpenHelper);
    contactDao = new ContactDataController(devOpenHelper);
  }

  public static UniDataController getInstance(Context mContext){
    if(dataController == null){
      synchronized (UniDataController.class){
        if(dataController == null){
          dataController = new UniDataController(mContext);
        }
      }
    }
    return dataController;
  }

  public DataController<Contact, String> contactDao() {
    return contactDao;
  }

  public DataController<App, String> appDao() {
    return appDao;
  }
}
