package org.uncle.lee.nativedatasource.controller;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.uncle.lee.nativedatasource.Entity.in.Contact;
import org.uncle.lee.nativedatasource.dao.ContactDao;
import org.uncle.lee.nativedatasource.dao.DaoMaster;
import org.uncle.lee.nativedatasource.dao.DaoSession;
import org.uncle.lee.nativedatasource.listener.ActionType;
import org.uncle.lee.nativedatasource.listener.DataListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class ContactDataController implements DataController<Contact, String> {
  private DataListener<Contact> listener;
  private ExecutorService threadPool;
  private ContactDao readContactDao;
  private ContactDao writeContactDao;

  public ContactDataController(DaoMaster.DevOpenHelper devOpenHelper, ExecutorService threadPool) {
    this.threadPool = threadPool;
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

  @Override public void insert(final Contact contact) {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        insertSyn(contact);
      }
    });
  }

  @Override
  public void setListener(DataListener<Contact> listener) {
    this.listener = listener;
  }

  private synchronized void insertSyn(Contact contact) {
    writeContactDao.insert(contact);
    this.listener.onAction(ActionType.INSERT_DONE, null);
  }

  @Override public void insert(final List<Contact> contacts) {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        insertSyn(contacts);
      }
    });
  }

  private synchronized void insertSyn(List<Contact> contacts){
    writeContactDao.insertInTx(contacts);
    this.listener.onAction(ActionType.INSERT_DONE, null);
  }

  @Override public void queryAll() {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        queryAllSyn();
      }
    });
  }

  private synchronized void queryAllSyn() {
    List<Contact> contactList = readContactDao.loadAll();
    this.listener.onAction(ActionType.QUERY_ALL_DONE, contactList);
  }

  @Override public void clean() {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        cleanSyn();
      }
    });
  }

  private synchronized void cleanSyn() {
    writeContactDao.deleteAll();
    this.listener.onAction(ActionType.CLEAN_DONE, null);
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(Contact origin, Contact target) {
    return false;
  }
}
