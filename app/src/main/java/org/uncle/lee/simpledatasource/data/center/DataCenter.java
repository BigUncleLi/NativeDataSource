package org.uncle.lee.simpledatasource.data.center;

import android.content.Context;

import java.util.List;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.listener.DataCenterListener;

/**
 * Created by Austin on 2016/7/7.
 */
public interface DataCenter {
  public abstract void setListener(DataCenterListener listener);
  public abstract void init(Context mContext);

  public abstract void queryContactList();
  public abstract void insertContactList(List<Contact> contactList);
  public abstract void cleanContactList();

  public abstract void queryAppList();
  public abstract void insertAppList(List<App> appList);
  public abstract void cleanAppList();
}
