package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.listener.ContentDaoListener;
import org.uncle.lee.simpledatasource.listener.DaoListener;

/**
 * Created by Austin on 2016/7/6.
 */
public class ContentDao<T, K> implements Dao<T, K> {
  private static ContentDao contentDao;
  private String uri;
  private ContentDaoListener<T> daoListener;

  private ContentDao() {}

  public static ContentDao getInstance(){
    if(contentDao == null){
      synchronized (ContentDao.class){
        if(contentDao == null){
          contentDao = new ContentDao();
        }
      }
    }
    return contentDao;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override public void insert(List<T> ts) {

  }

  @Override public void queryAll() {

  }

  @Override public boolean deleteByKeyword(K k) {
    return false;
  }

  @Override public boolean update(T origin, T target) {
    return false;
  }

  @Override public void clean() {

  }

  @Override public void setListener(DaoListener<T> daoListener) {
  }
}
