package org.uncle.lee.simpledatasource.data.center;

import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;

/**
 * Created by Austin on 2016/7/7.
 */
public interface DataCenter {
  public abstract List<Contact> getContactList();
  public abstract void setContactList(List<Contact> contactList);
  public abstract List<App> getAppList();
  public abstract void setAppList(List<App> appList);
}
