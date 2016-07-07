package org.uncle.lee.simpledatasource.controller;

import java.util.List;
import org.uncle.lee.simpledatasource.listener.DataControllerListener;

/**
 * Created by Austin on 2016/7/6.
 */
public interface DataController<T, K> {
  public static final String DATABASE_NAME = "uniInfo-db";

  public abstract void insert(List<T> tList);

  public abstract void queryAll();

  public abstract void clean();

  public abstract void insert(T t);

  public abstract void setListener(DataControllerListener<T> listener);

  @Deprecated
  public abstract boolean deleteByKeyword(K k);

  @Deprecated
  public abstract boolean update(T origin, T target);
}
