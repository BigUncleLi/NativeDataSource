package org.uncle.lee.nativedatasource.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.uncle.lee.nativedatasource.Entity.in.App;
import org.uncle.lee.nativedatasource.dao.AppDao;
import org.uncle.lee.nativedatasource.dao.DaoMaster;
import org.uncle.lee.nativedatasource.dao.DaoSession;
import org.uncle.lee.nativedatasource.listener.ActionType;
import org.uncle.lee.nativedatasource.listener.DataListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDataController implements DataController<App, String> {
  public static final String TAG = AppDataController.class.getSimpleName();

  private DataListener<App> listener;
  private ExecutorService threadPool;
  private AppDao readAppDao;
  private AppDao writeAppDao;

  public AppDataController(DaoMaster.DevOpenHelper devOpenHelper, ExecutorService threadPool) {
    this.threadPool = threadPool;
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

  @Override public void insert(final App app) {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        insertSyn(app);
      }
    });
  }

  @Override
  public void setListener(DataListener<App> listener) {
    this.listener = listener;
  }

  private synchronized void insertSyn(App app) {
    Log.d(TAG, "insert syn...");
    writeAppDao.insert(app);
    this.listener.onAction(ActionType.INSERT_DONE, null);
  }

  @Override public void insert(final List<App> apps) {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        insertSyn(apps);
      }
    });
  }

  private synchronized void insertSyn(List<App> apps){
    Log.d(TAG, "insert list syn...");
    writeAppDao.insertInTx(apps);
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
    Log.d(TAG, "query syn...");
    List<App> appList = readAppDao.loadAll();
    this.listener.onAction(ActionType.QUERY_ALL_DONE, appList);
  }

  @Override public void clean() {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        cleanSyn();
      }
    });
  }

  private synchronized void cleanSyn() {
    Log.d(TAG, "clean syn...");
    writeAppDao.deleteAll();
    this.listener.onAction(ActionType.CLEAN_DONE, null);
  }

  public void queryByName(String name){}
  public void queryById(String id){}

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }
}
