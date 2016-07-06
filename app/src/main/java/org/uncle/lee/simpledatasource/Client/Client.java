package org.uncle.lee.simpledatasource.Client;

import java.util.List;
import java.util.Objects;
import org.uncle.lee.simpledatasource.controller.DataController;
import org.uncle.lee.simpledatasource.dao.Dao;
import org.uncle.lee.simpledatasource.entity.App;
import org.uncle.lee.simpledatasource.entity.Contact;
import org.uncle.lee.simpledatasource.listener.ContactListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class Client {
  public static void main(String[] args) {
    DataController instance = DataController.getInstance();

    testContact(instance);
    testApp(instance);
  }

  private static void testApp(DataController instance) {
    Dao<Contact, String> contactDao = instance.contactDao();
    contactDao.setListener(new ContactListener() {
      @Override public void onActionDone(Dao.DaoActionType type, List<Contact> contacts) {

      }
    });
    contactDao.queryAll();
  }

  private static void testContact(DataController instance) {
    Dao<App, String> appDao = instance.appDao();
  }
}
