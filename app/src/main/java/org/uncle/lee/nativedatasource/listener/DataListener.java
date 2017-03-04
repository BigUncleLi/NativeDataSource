package org.uncle.lee.nativedatasource.listener;

import java.util.List;

/**
 * Created by Austin on 2016/7/7.
 */
public interface DataListener<T> {
  public abstract void onAction(ActionType actionType, List<T> dataList);
}
