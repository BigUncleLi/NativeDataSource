package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.entity.App;
import org.uncle.lee.simpledatasource.listener.DaoListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDao implements Dao<App, String>{
  @Override public void insert(List<App> apps) {

  }

  @Override public void queryAll() {

  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }

  @Override public void clean() {

  }

  @Override public void setListener(DaoListener<App> daoListener) {

  }
}
