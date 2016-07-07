package org.uncle.lee.simpledatasource.listener;

import java.util.List;

/**
 * Created by Austin on 2016/7/7.
 */
public interface UniDataCenterListener {
  public abstract void onAction(ActionType actionType, boolean isSuccess, List<?> dataList);

  public enum ActionType{
    INSERT_DONE,
    QUERY_ALL_DONE,
    CLEAN_DONE;
  }
}
