package org.uncle.lee.simpledatasource.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.dao.AppDao;
import org.uncle.lee.simpledatasource.dao.DaoMaster;
import org.uncle.lee.simpledatasource.dao.DaoSession;
import org.uncle.lee.simpledatasource.listener.DataControllerListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDataController extends AbstractDataController<App, String> {
  public static final String TAG = AppDataController.class.getSimpleName();

  private DataControllerListener<App> listener;
  private ExecutorService threadPool;
  private AppDao readAppDao;
  private AppDao writeAppDao;
  private App appTemp;

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

  @Override public void insert(App app) {
    this.appTemp = app;
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        insertSyn(appTemp);
      }
    });
  }

  private synchronized void insertSyn(App app) {
    Log.d(TAG, "insert syn...");
    writeAppDao.insert(app);
    this.listener.onAction(DataControllerListener.ActionType.INSERT_DONE, true, null);
  }

  @Override public void queryAll() {
    this.threadPool.submit(new Runnable() {
      @Override public void run() {
        queryAll();
      }
    });
  }

  private synchronized void queryAllSyn() {
    Log.d(TAG, "query syn...");
    List<App> appList = readAppDao.loadAll();
    this.listener.onAction(DataControllerListener.ActionType.QUERY_ALL_DONE, true, appList);
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
    this.listener.onAction(DataControllerListener.ActionType.CLEAN_DONE, true, null);
  }

  @Override public void setListener(DataControllerListener<App> listener) {
    this.listener = listener;
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }
}
