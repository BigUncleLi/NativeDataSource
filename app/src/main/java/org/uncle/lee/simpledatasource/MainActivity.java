package org.uncle.lee.simpledatasource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.uncle.lee.simpledatasource.entity.Contact;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    craeteDb();
    Contact entity = createEntity();
    saveInDb(entity);
  }

  private void craeteDb() {
  }

  private Contact createEntity() {
    Contact contact = new Contact();
    contact.setId((long)999);
    contact.setName("张三");
    contact.setPy("zhangsan");
    contact.setName("10086");
    return contact;
  }

  private void saveInDb(Contact entity){

  }
}
