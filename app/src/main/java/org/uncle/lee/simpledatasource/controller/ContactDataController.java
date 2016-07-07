package org.uncle.lee.simpledatasource.controller;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.dao.ContactDao;
import org.uncle.lee.simpledatasource.dao.DaoMaster;
import org.uncle.lee.simpledatasource.dao.DaoSession;

/**
 * Created by Austin on 2016/7/6.
 */
public class ContactDataController extends AbstractDataController<Contact, String> {
  private ContactDao readContactDao;
  private ContactDao writeContactDao;

  public ContactDataController(DaoMaster.DevOpenHelper devOpenHelper) {
    readContactDao = createReadContactDao(devOpenHelper);
    writeContactDao = createWriteContactDao(devOpenHelper);
  }

  private ContactDao createReadContactDao(DaoMaster.DevOpenHelper devOpenHelper) {
    SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
    DaoMaster daoMaster = new DaoMaster(readableDatabase);
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getContactDao();
  }

  private ContactDao createWriteContactDao(DaoMaster.DevOpenHelper devOpenHelper) {
    SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(writableDatabase);
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getContactDao();
  }

  @Override public List<Contact> queryAll() {
    return readContactDao.loadAll();
  }

  @Override public void clean() {
    writeContactDao.deleteAll();
  }

  @Override public void insert(Contact contact) {
    writeContactDao.insert(contact);
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(Contact origin, Contact target) {
    return false;
  }
}
