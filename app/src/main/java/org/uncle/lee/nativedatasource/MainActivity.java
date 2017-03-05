package org.uncle.lee.nativedatasource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import org.uncle.lee.nativedatasource.Entity.in.App;
import org.uncle.lee.nativedatasource.Entity.in.Contact;
import org.uncle.lee.nativedatasource.data.center.UniDataCenter;
import org.uncle.lee.nativedatasource.listener.ActionType;
import org.uncle.lee.nativedatasource.listener.DataListener;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "uniDatabase";
  public static final int MAX_NUMBER = 10000;
  private UniDataCenter instance;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    initUniDataCenter();
  }

  private void initUniDataCenter() {
    instance = UniDataCenter.getInstance(this);
  }

  private void initView() {
    Button bt_insert_app = (Button) findViewById(R.id.bt_insert_app);
    Button bt_query_apps = (Button) findViewById(R.id.bt_query_apps);
    Button bt_clean_apps = (Button) findViewById(R.id.bt_clean_apps);
    Button bt_insert_contact = (Button) findViewById(R.id.bt_insert_contact);
    Button bt_query_contacts = (Button) findViewById(R.id.bt_query_contacts);
    Button bt_clean_contacts = (Button) findViewById(R.id.bt_clean_contacts);

    bt_insert_app.setOnClickListener(mOnclickListener);
    bt_query_apps.setOnClickListener(mOnclickListener);
    bt_clean_apps.setOnClickListener(mOnclickListener);
    bt_insert_contact.setOnClickListener(mOnclickListener);
    bt_query_contacts.setOnClickListener(mOnclickListener);
    bt_clean_contacts.setOnClickListener(mOnclickListener);
  }

  View.OnClickListener mOnclickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      switch(v.getId()){
        case R.id.bt_insert_app:
          Log.d(TAG, "insert start ... ");
          insertApps(createAppList());
          break;
        case R.id.bt_query_apps:
          Log.d(TAG, "query app start ... ");
          queryApps();
          break;
        case R.id.bt_clean_apps:
          Log.d(TAG, "clean app start ... ");
          cleanApps();
          break;
        case R.id.bt_insert_contact:
          Log.d(TAG, "insert contact start ... ");
          insertContact(createContactList());
          break;
        case R.id.bt_query_contacts:
          Log.d(TAG, "query contacts start ... ");
          queryContact();
          break;
        case R.id.bt_clean_contacts:
          Log.d(TAG, "clean contacts start ... ");
          cleanContact();
          break;
      }
    }

    private <T> void show(List<T> ts){
      for(T t : ts){
        Log.d(TAG, t.toString());
      }
    }
  };

  private void cleanContact() {
    instance.setListener(new DataListener<String>() {
      @Override public void onAction(ActionType actionType, List<String> dataList) {
        if(actionType.equals(ActionType.CLEAN_DONE)){
          Log.d(TAG, "clean done");
        }
      }
    });
    instance.cleanContactList();
  }

  private void queryContact() {
    instance.setListener(new DataListener<Contact>() {
      @Override public void onAction(ActionType actionType, List<Contact> dataList) {
        if(actionType.equals(ActionType.QUERY_ALL_DONE)){
          Log.d(TAG, "contact number : " + dataList.size());
        }
      }
    });
    instance.queryContactList();
  }

  private void insertContact(List<Contact> contactList) {
    instance.setListener(new DataListener<String>() {
      @Override public void onAction(ActionType actionType, List<String> dataList) {
        if(actionType.equals(ActionType.INSERT_DONE)){
          Log.d(TAG, "insert done");
        }
      }
    });
    instance.insertContactList(contactList);
  }

  private void cleanApps() {
    instance.setListener(new DataListener<String>() {
      @Override public void onAction(ActionType actionType, List<String> dataList) {
        if(actionType.equals(ActionType.CLEAN_DONE)){
          Log.d(TAG, "clean done");
        }
      }
    });
    instance.cleanAppList();
  }

  private void queryApps() {
    instance.setListener(new DataListener<App>() {
      @Override public void onAction(ActionType actionType, List<App> dataList) {
        if(actionType.equals(ActionType.QUERY_ALL_DONE)){
          Log.d(TAG, "app number : " + dataList.size());
        }
      }
    });
    instance.queryAppList();
  }

  private void insertApps(List<App> appList) {
    instance.setListener(new DataListener<String>() {
      @Override public void onAction(ActionType actionType, List<String> dataList) {
        if(actionType.equals(ActionType.INSERT_DONE)){
          Log.d(TAG, "insert apps done");
        }
      }
    });
    instance.insertAppList(appList);
  }

  private List<App> appList = new ArrayList<App>();
  private List<App> createAppList() {
    appList.clear();
    for(int i = 0; i < MAX_NUMBER; i++){
      App app = new App(null, "app" + i, "应用" + i, "pyLabe" + i, "className" + i, "nickName" + i);
      appList.add(app);
    }
    return appList;
  }

  private List<Contact> createContactList() {
    List<Contact> contactList = new ArrayList<Contact>();
    for(int i = 0; i < MAX_NUMBER; i++){
      Contact contact = new Contact(null, "张三", "pyName" + i, "10086", false);
      contactList.add(contact);
    }
    return contactList;
  }
}
