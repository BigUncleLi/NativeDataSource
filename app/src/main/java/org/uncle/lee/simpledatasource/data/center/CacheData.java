package org.uncle.lee.simpledatasource.data.center;

import java.util.Collections;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Entity.out.UniApp;
import org.uncle.lee.simpledatasource.Entity.out.UniContact;
import org.uncle.lee.simpledatasource.Utils.Transformer;

/**
 * Created by Austin on 2016/7/7.
 */
public class CacheData {
  private List<App> inputAppList;
  private List<Contact> inputContact;

  private List<UniApp> outputAppList;
  private List<UniContact> outputContactList;

  public void saveCacheAppList(List<App> apps) {
    outputAppList = Transformer.transformUniApp(apps);
  }

  public void saveCacheContactList(List<Contact> contacts) {
    //outputContactList = Transformer.transformUniContact(contacts);
  }

  public List<UniApp> cacheAppList(){
    return outputAppList;
  }

  public List<UniContact> cacheContactList(){
    return outputContactList;
  }
}
