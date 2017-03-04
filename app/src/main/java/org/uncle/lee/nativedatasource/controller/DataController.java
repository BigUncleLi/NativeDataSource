package org.uncle.lee.nativedatasource.controller;

import java.util.List;
import org.uncle.lee.nativedatasource.listener.DataListener;

/**
 * Created by Austin on 2016/7/6.
 */
public interface DataController<T, K> {
  public static final String DATABASE_NAME = "uniInfo-db";

  public abstract void insert(List<T> tList);

  public abstract void queryAll();

  public abstract void clean();

  public abstract void insert(T t);

  public abstract void setListener(DataListener<T> listener);

  @Deprecated
  public abstract boolean deleteByKeyword(K k);

  @Deprecated
  public abstract boolean update(T origin, T target);
}
