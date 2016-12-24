package org.uncle.lee.simpledatasource.listener;

import java.util.List;

/**
 * Created by Austin on 2016/7/7.
 */
public interface DataCenterListener {
  public abstract void onAction(ActionType actionType, List<?> dataList);
}
