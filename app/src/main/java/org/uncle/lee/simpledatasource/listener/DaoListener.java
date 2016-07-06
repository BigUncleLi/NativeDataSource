package org.uncle.lee.simpledatasource.listener;

import java.util.List;
import java.util.Objects;
import org.uncle.lee.simpledatasource.dao.Dao;

/**
 * Created by Austin on 2016/7/6.
 */
public interface DaoListener <T>{
  public abstract void onActionDone(Dao.DaoActionType type, List<T> tList);
}
