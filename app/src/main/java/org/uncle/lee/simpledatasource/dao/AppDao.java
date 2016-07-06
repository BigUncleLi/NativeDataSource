package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.entity.App;
import org.uncle.lee.simpledatasource.listener.ContentDaoListener;
import org.uncle.lee.simpledatasource.listener.DaoListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDao implements Dao<App, String>{
  private static final String APP_URI = "app_uri";
  private ContentDao contentDao;

  public AppDao(){
    contentDao = ContentDao.getInstance();
    contentDao.setListener(daoListener);
  }

  @Override public void insert(List<App> apps) {
    contentDao.insert(apps);
  }

  @Override public void queryAll() {
    contentDao.queryAll();
  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }

  @Override public void clean() {
    contentDao.clean();
  }

  @Override public void setListener(DaoListener<App> daoListener) {}

  public ContentDaoListener daoListener = new ContentDaoListener() {
    @Override public void onActionDone(DaoActionType type, List list) {

    }
  };
}
