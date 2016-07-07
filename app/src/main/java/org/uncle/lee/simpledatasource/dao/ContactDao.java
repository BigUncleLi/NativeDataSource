package org.uncle.lee.simpledatasource.dao;

import java.util.List;
import org.uncle.lee.simpledatasource.Entity.Contact;

/**
 * Created by Austin on 2016/7/6.
 */
public class ContactDao implements Dao<Contact, String>{

  @Override public void insert(List<Contact> contacts) {

  }

  @Override public void queryAll() {

  }

  @Override public void clean() {

  }

  @Override public boolean deleteByKeyword(String s) {
    return false;
  }

  @Override public boolean update(Contact origin, Contact target) {
    return false;
  }
}
