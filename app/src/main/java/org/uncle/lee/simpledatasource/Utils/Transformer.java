package org.uncle.lee.simpledatasource.Utils;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Entity.out.UniApp;
import org.uncle.lee.simpledatasource.Entity.out.UniContact;
import org.uncle.lee.simpledatasource.Utils.pinyin.pinyin4j.PinYin4j;

/**
 * Created by Austin on 2016/7/7.
 */
public class Transformer {
  public List<UniApp> transformUniApp(List<UniApp> originUniAppList, List<App> newAppList) {
    for (App app : newAppList) {
      UniApp uniApp = transform2Uniapp(app);
      originUniAppList.add(uniApp);
      app = null;
      uniApp = null;
    }
    return originUniAppList;
  }

  private UniApp transform2Uniapp(App app) {
    UniApp uniApp = new UniApp();
    uniApp.setmPackageName(app.getPackageName());
    uniApp.setmAppLabel(app.getAppLabel());
    uniApp.setmAppLabelPinYin(app.getAppPyLabel());
    return uniApp;
  }

  private List<UniContact> originUniContactList;

  public List<UniContact> transformUniContact(List<UniContact> originUniContactList,
      List<Contact> contactList) {
    this.originUniContactList = originUniContactList;
    transformUniContact(contactList);
    return originUniContactList;
  }

  private void transformUniContact(List<Contact> contactList) {
    for (Contact contact : contactList) {
      UniContact uniContact = createUniContact(contact, contactList);
      this.originUniContactList.add(uniContact);
      uniContact = null;
      contact = null;
    }
  }

  private UniContact createUniContact(Contact contact, List<Contact> contactList) {
    UniContact uniContact = new UniContact();
    uniContact.setContactName(contact.getName());
    uniContact.setContactNamePinYin(contact.getPy());

    return uniContact;
  }

  private ArrayList<String> getNumberList(Contact contact, List<Contact> contactList,
      UniContact uniContact) {
    ArrayList<String> numberList = new ArrayList<String>();
    numberList.add(contact.getNumber());
    for (Contact contactItem : contactList) {
      if (contactItem.getName().equals(uniContact.getContactName())) {
        numberList.add(contactItem.getNumber());
      }
      contactItem = null;
    }
    return numberList;
  }

  @Deprecated public List<App> addPyForApp(Context mContext, List<App> appList) {
    return appList;
  }

  public List<Contact> addPyForContact(final Context mContext, List<Contact> contactList) {
    final CountDownLatch countDownLatch = new CountDownLatch(contactList.size());
    ExecutorService exec =
        new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    for (final Contact contact : contactList) {
      exec.execute(new Runnable() {
        @Override public void run() {
          contact.setId(null);
          contact.setPy(transform2Py(mContext, contact.getName()));
          countDownLatch.countDown();
        }
      });
    }
    try {
      countDownLatch.await();
      return contactList;
    } catch (Exception e) {
    }
    return null;
  }

  public String transform2Py(Context mContext, String text) {
    return PinYin4j.converterToSpell(text);
  }
}
