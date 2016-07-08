package org.uncle.lee.simpledatasource.Utils;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.in.App;
import org.uncle.lee.simpledatasource.Entity.in.Contact;
import org.uncle.lee.simpledatasource.Entity.out.UniApp;
import org.uncle.lee.simpledatasource.Entity.out.UniContact;
import org.uncle.lee.simpledatasource.Utils.pinyin.pinyin4j.PinYin4j;

/**
 * Created by Austin on 2016/7/7.
 */
public class Transformer {
  public static List<UniApp> transformUniApp(List<App> appList){
    List<UniApp> uniAppList = new ArrayList<UniApp>();
    for(App app : appList){
      UniApp uniApp = transform2Uniapp(app);
      uniAppList.add(uniApp);
    }
    return uniAppList;
  }

  private static UniApp transform2Uniapp(App app) {
    UniApp uniApp = new UniApp();
    uniApp.setmPackageName(app.getPackageName());
    uniApp.setmAppLabel(app.getAppLabel());
    uniApp.setmAppLabelPinYin(app.getAppPyLabel());
    return uniApp;
  }

  public static List<UniContact> transformUniContact(List<Contact> contactList){
    List<UniContact> uniContactList = new ArrayList<UniContact>();
    for(Contact contact : contactList){
      UniContact uniContact = transform2Unicontact(contact, contactList);
      uniContactList.add(uniContact);
    }
    return uniContactList;
  }

  private static UniContact transform2Unicontact(Contact contact, List<Contact> contactList) {
    UniContact uniContact = new UniContact();
    uniContact.setContactName(contact.getName());
    uniContact.setContactNamePinYin(contact.getPy());

    ArrayList<String> numberList = getNumberList(contact, contactList, uniContact);
    uniContact.setContactPhoneNO(numberList);
    return uniContact;
  }

  private static ArrayList<String> getNumberList(Contact contact, List<Contact> contactList,
      UniContact uniContact) {
    ArrayList<String> numberList = new ArrayList<String>();
    numberList.add(contact.getNumber());
    for(Contact contactItem : contactList){
      if(contactItem.getName().equals(uniContact.getContactName())){
        numberList.add(contactItem.getNumber());
      }
    }
    return numberList;
  }

  public static List<App> addPyForApp(Context mContext, List<App> appList){
    for(App app : appList){
      app.setId(null);
      app.setAppPyLabel(transform2Py(mContext, app.getAppLabel()));
    }
    return appList;
  }

  public static List<Contact> addPyForContact(Context mContext, List<Contact> contactList){
    for(Contact contact : contactList){
      contact.setId(null);
      contact.setPy(transform2Py(mContext, contact.getName()));
    }
    return contactList;
  }

  public static String transform2Py(Context mContext, String text){
    return PinYin4j.converterToSpell(text);
  }
}
