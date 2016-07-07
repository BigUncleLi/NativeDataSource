package org.uncle.lee.simpledatasource.controller;

import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.dao.AppDao;
import org.uncle.lee.simpledatasource.dao.ContactDao;
import org.uncle.lee.simpledatasource.dao.Dao;

/**
 * Created by Austin on 2016/7/6.
 */
public class DataController {
  private static DataController dataController;
  private Dao<Contact, String> contactDao;
  private Dao<App, String> appDao;

  private DataController(){
    appDao = new AppDao();
    contactDao = new ContactDao();
  }

  public static DataController getInstance(){
    if(dataController == null){
      synchronized (DataController.class){
        if(dataController == null){
          dataController = new DataController();
        }
      }
    }
    return dataController;
  }

  public Dao<Contact, String> contactDao() {
    return contactDao;
  }

  public Dao<App, String> appDao() {
    return appDao;
  }
}
