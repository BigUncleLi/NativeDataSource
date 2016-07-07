package org.uncle.lee.simpledatasource.dao;

import java.util.List;

/**
 * Created by Austin on 2016/7/6.
 */
public interface Dao<T, K> {
  public abstract void insert(List<T> tList);

  public abstract void queryAll();

  public abstract void clean();

  public abstract boolean deleteByKeyword(K k);

  public abstract boolean update(T origin, T target);
}
