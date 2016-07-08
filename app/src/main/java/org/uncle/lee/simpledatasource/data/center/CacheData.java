package org.uncle.lee.simpledatasource.data.center;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Entity.out.UniApp;
import org.uncle.lee.simpledatasource.Entity.out.UniContact;
import org.uncle.lee.simpledatasource.Utils.Transformer;

/**
 * Created by Austin on 2016/7/7.
 */
public class CacheData {
  private Queue<UniContact> uniContactQueue;
  private static final int CACHE_MAX_SIZE = 30;

  public CacheData(){
    uniContactQueue = new ArrayBlockingQueue<UniContact>(CACHE_MAX_SIZE);
  }

  public void insertCacheContact(UniContact uniContact){
    if(isFullQueue()){
      uniContactQueue.remove();
    }
    uniContactQueue.add(uniContact);
  }

  private boolean isFullQueue() {
    return uniContactQueue.size() == CACHE_MAX_SIZE;
  }

  public Queue<UniContact> uniContactQueue() {
    return uniContactQueue;
  }
}
