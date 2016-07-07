package org.uncle.lee.simpledatasource;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;
import org.uncle.lee.simpledatasource.Entity.App;
import org.uncle.lee.simpledatasource.Entity.Contact;
import org.uncle.lee.simpledatasource.controller.pub.UniDataController;

public class MainActivity extends AppCompatActivity {
  private UniDataController instance;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    initUniDataController();
  }

  private void initUniDataController() {
    instance = UniDataController.getInstance(this);
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
          instance.appDao().insert(new App(null,"app1", "applabel", "pyLabel", "className"));
          break;
        case R.id.bt_query_apps:
          List<App> apps = instance.appDao().queryAll();
          show(apps);
          break;
        case R.id.bt_clean_apps:
          instance.appDao().clean();
          break;
        case R.id.bt_insert_contact:
          instance.contactDao().insert(new Contact(null,"contactA", "pycontact", "10086"));
          break;
        case R.id.bt_query_contacts:
          show(instance.contactDao().queryAll());
          break;
        case R.id.bt_clean_contacts:
          instance.contactDao().clean();
          break;
      }
    }

    private <T> void show(List<T> ts){
      for(T t : ts){
        Log.d("uniDatabase", t.toString());
      }
    }
  };
}
