package org.uncle.lee.simpledatasource.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
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

  public List<UniContact> transformUniContact(List<UniContact> originUniContactList,
      List<Contact> contactList) {
    List<UniContact> newUniContactList = transform(contactList);
    return combineOldAndNewUniContactList(originUniContactList, newUniContactList);
  }

  private List<UniContact> transform(List<Contact> contactList) {
    List<UniContact> uniContactList = new ArrayList<UniContact>();
    HashMap<String, UniContact> map = new HashMap<String, UniContact>();
    for(Contact contact : contactList){
      if(map.containsKey(contact.getName())){
        UniContact uniContact = map.get(contact.getName());
        uniContact.getContactPhoneNO().add(contact.getNumber());
      }else{
        UniContact uniContact = createUniContact(contact);
        map.put(contact.getName(), uniContact);
        uniContactList.add(uniContact);
      }
    }
    return uniContactList;
  }

  private List<UniContact> combineOldAndNewUniContactList(List<UniContact> originUniContactList,
      List<UniContact> newUniContactList) {
    originUniContactList.addAll(newUniContactList);
    return combineMultiUniContact(originUniContactList);
  }

  private List<UniContact> combineMultiUniContact(List<UniContact> originUniContactList) {
    HashMap<String, UniContact> map = new HashMap<String, UniContact>();
    List<UniContact> singleUnicontactList = new ArrayList<UniContact>();
    for(UniContact uniContact : originUniContactList){
      if(map.containsKey(uniContact.getContactName())){
        UniContact uniContactTemp = map.get(uniContact.getContactName());
        uniContactTemp.getContactPhoneNO().addAll(uniContact.getContactPhoneNO());
      }else {
        map.put(uniContact.getContactName(), uniContact);
        singleUnicontactList.add(uniContact);
      }
    }
    return singleUnicontactList;
  }

  @NonNull private UniContact createUniContact(Contact contact) {
    UniContact uniContact = new UniContact();
    uniContact.setContactName(contact.getName());
    uniContact.setContactNamePinYin(contact.getPy());
    uniContact.getContactPhoneNO().add(contact.getNumber());
    return uniContact;
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
