package org.uncle.lee.nativedatasource.data.center;

import android.content.Context;

import java.util.List;
import org.uncle.lee.nativedatasource.Entity.in.App;
import org.uncle.lee.nativedatasource.Entity.in.Contact;
import org.uncle.lee.nativedatasource.listener.DataListener;

/**
 * all functions provided for users
 */
interface DataCenter {
  void setListener(DataListener<?> listener);
  void init(Context mContext);

  void queryContactList();
  void insertContactList(List<Contact> contactList);
  void cleanContactList();

  void queryAppList();
  App queryAppByName(String name);
  App queryAppByPackageName(String packageName);
  void insertAppList(List<App> appList);
  void cleanAppList();
}
