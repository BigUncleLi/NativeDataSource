package org.uncle.lee.simpledatasource.controller;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.dao.AppDao;
import org.uncle.lee.simpledatasource.dao.DaoMaster;
import org.uncle.lee.simpledatasource.dao.DaoSession;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDataController extends AbstractDataController<App, String> {
  private AppDao readAppDao;
  private AppDao writeAppDao;

  public AppDataController(DaoMaster.DevOpenHelper devOpenHelper) {
    readAppDao = createReadAppDao(devOpenHelper);
    writeAppDao = createWriteAppDao(devOpenHelper);
  }

  private AppDao createWriteAppDao(DaoMaster.DevOpenHelper devOpenHelper) {
    SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(writableDatabase);
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getAppDao();
  }

  private AppDao createReadAppDao(DaoMaster.DevOpenHelper devOpenHelper) {
    SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
    DaoMaster daoMaster = new DaoMaster(readableDatabase);
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getAppDao();
  }

  @Override public List<App> queryAll() {
    return readAppDao.loadAll();
  }

  @Override public void clean() {
    writeAppDao.deleteAll();
  }

  @Override public void insert(App app) {
    writeAppDao.insert(app);
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }
}
