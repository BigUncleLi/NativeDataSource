package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.listener.DaoListener;

/**
 * Created by Austin on 2016/7/6.
 */
public interface Dao<T, K> {
  public abstract void insert(List<T> tList);

  public abstract void queryAll();

  public abstract boolean deleteByKeyword(K k);

  public abstract boolean update(T origin, T target);

  public abstract void clean();

  public abstract void setListener(DaoListener<T> daoListener);

  public enum DaoActionType {
    INIT_DONE(false),
    INSERT_DONE(false),
    QUERY_DONE(false),
    DELETE_ONCE(false),
    UPDATE(false),
    CLEAN(false);

    public boolean isSuccess;

    private DaoActionType(boolean isSuccess) {
      this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
      return isSuccess;
    }

    public void setSuccess(boolean success) {
      isSuccess = success;
    }
  }
}
