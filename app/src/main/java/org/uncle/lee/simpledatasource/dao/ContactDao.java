package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.entity.Contact;
import org.uncle.lee.simpledatasource.listener.ContentDaoListener;
import org.uncle.lee.simpledatasource.listener.DaoListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class ContactDao implements Dao<Contact, String>{
  private static final String CONTACT_URI = "contact_uri";
  private ContentDao contentDao;

  public ContactDao() {
    this.contentDao = ContentDao.getInstance();
    contentDao.setListener(daoListener);
  }

  @Override public void insert(List<Contact> contacts) {
    contentDao.insert(contacts);
  }

  @Override public void queryAll() {
    contentDao.queryAll();
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(Contact origin, Contact target) {
    return false;
  }

  @Override public void clean() {

  }

  @Override public void setListener(DaoListener<Contact> daoListener) {}

  public ContentDaoListener daoListener = new ContentDaoListener() {
    @Override public void onActionDone(DaoActionType type, List list) {

    }
  };
}
