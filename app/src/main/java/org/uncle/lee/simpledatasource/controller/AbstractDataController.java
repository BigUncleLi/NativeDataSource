package org.uncle.lee.simpledatasource.controller;

import java.util.List;

/**
 * Created by Austin on 2016/7/7.
 */
public abstract class AbstractDataController<T, K> implements DataController<T,K> {
  @Override public void insert(List<T> ts) {
    for(T t : ts){
      insert(t);
    }
  }
}
