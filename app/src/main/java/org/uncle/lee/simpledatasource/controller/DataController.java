package org.uncle.lee.simpledatasource.controller;

import org.uncle.lee.simpledatasource.dao.AppDao;
import org.uncle.lee.simpledatasource.dao.ContactDao;
import org.uncle.lee.simpledatasource.dao.Dao;
import org.uncle.lee.simpledatasource.entity.App;
import org.uncle.lee.simpledatasource.entity.Contact;

/**
 * Created by Austin on 2016/7/6.
 */
public class DataController {
  private static DataController dataController;
  private Dao<Contact, String> contactDao;
  private Dao<App, String> appDao;

  private DataController(){}

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

  public void init(){
    initContactDao();
    initAppDat();
  }

  private void initAppDat() {
    appDao = new AppDao();
    appDao.init();
  }

  private void initContactDao() {
    contactDao = new ContactDao();
    contactDao.init();
  }

  public Dao<Contact, String> contactDao() {
    return contactDao;
  }

  public Dao<App, String> appDao() {
    return appDao;
  }
}
