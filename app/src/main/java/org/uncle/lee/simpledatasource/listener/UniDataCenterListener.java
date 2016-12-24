package org.uncle.lee.simpledatasource.listener;

import java.util.List;

/**
 * Created by Austin on 2016/7/7.
 */
public interface UniDataCenterListener {
  public abstract void onAction(ActionType actionType, List<?> dataList);

  public enum ActionType{
    INIT_DONE,
    INIT_ERROR,
    INSERT_DONE,
    INSERT_ERROR,
    QUERY_ALL_DONE,
    QUERY_ALL_ERROR,
    CLEAN_DONE,
    CLEAN_ERROR;
  }
}
