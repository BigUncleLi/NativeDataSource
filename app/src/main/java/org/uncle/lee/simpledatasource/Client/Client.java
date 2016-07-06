package org.uncle.lee.simpledatasource.Client;

import java.util.ArrayList;
import java.util.List;
import org.uncle.lee.simpledatasource.controller.DataController;
import org.uncle.lee.simpledatasource.dao.Dao;
import org.uncle.lee.simpledatasource.entity.App;
import org.uncle.lee.simpledatasource.entity.Contact;
import org.uncle.lee.simpledatasource.listener.AppListener;
import org.uncle.lee.simpledatasource.listener.ContactListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class Client {
  public static void main(String[] args) {
    DataController instance = DataController.getInstance();
    Dao<App, String> appDao = instance.appDao();
    Dao<Contact, String> contactDao = instance.contactDao();

    testAppWrite(appDao);
    testContactWrite(contactDao);

    testAppQuery(appDao);
    testContactQuery(contactDao);
  }

  private static void testContactWrite(Dao<Contact, String> contactDao) {
    List<Contact> contactList = getContactList();
    contactDao.setListener(new ContactListener() {
      @Override public void onActionDone(Dao.DaoActionType type, List<Contact> contacts) {

      }
    });
    contactDao.insert(contactList);
  }

  private static List<Contact> getContactList() {
    List<Contact> contactList = new ArrayList<Contact>();
    contactList.add(new Contact(Long.parseLong("1"), "张三", "zhangsan", "10086"));
    contactList.add(new Contact(Long.parseLong("2"), "张三", "zhangsan", "10000"));
    contactList.add(new Contact(Long.parseLong("3"), "章三", "zhangsan", "123123"));
    return contactList;
  }

  private static void testAppWrite(Dao<App, String> appDao) {
    List<App> appList = getAppList();
    appDao.setListener(new AppListener() {
      @Override public void onActionDone(Dao.DaoActionType type, List<App> apps) {

      }
    });
    appDao.insert(appList);
  }

  private static List<App> getAppList() {
    List<App> appList = new ArrayList<App>();
    appList.add(new App("packageNameA", "laberA", "pyLaberA", "classNameA"));
    appList.add(new App("packageNameB", "laberB", "pyLaberB", "classNameB"));
    appList.add(new App("packageNameC", "laberC", "pyLaberC", "classNameC"));
    return appList;
  }

  private static void testAppQuery(Dao<App, String> appDao) {
    appDao.setListener(new AppListener() {
      @Override public void onActionDone(Dao.DaoActionType type, List<App> apps) {

      }
    });
    appDao.queryAll();
  }

  private static void testContactQuery(Dao<Contact, String> contactDao) {
    contactDao.setListener(new ContactListener() {
      @Override public void onActionDone(Dao.DaoActionType type, List<Contact> contacts) {

      }
    });
    contactDao.queryAll();
  }
}
