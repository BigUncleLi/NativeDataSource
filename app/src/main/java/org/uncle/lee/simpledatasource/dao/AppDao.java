package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;

/**
 * Created by Austin on 2016/7/6.
 */
public class AppDao implements Dao<App, String>{
  @Override public void insert(List<App> apps) {

  }

  @Override public void queryAll() {

  }

  @Override public void clean() {

  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(App origin, App target) {
    return false;
  }
}
