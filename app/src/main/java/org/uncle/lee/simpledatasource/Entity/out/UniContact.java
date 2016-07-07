package org.uncle.lee.simpledatasource.Entity.out;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxin on 2016/3/25 0025.
 */
public class UniContact {

  private int contactId = -1;
  private String contactName = "";
  private String contactNamePinYin = "";
  private int photoId = 0;
  private int hasPhoneNumber = 0;
  private ArrayList<String> contactPhoneNO = new ArrayList<>();

  private List<String> spellWordsList;

  public UniContact() {
  }

  public UniContact(int contactId, String contactName, String contactNamePinYin, int photoId,
      int hasPhoneNumber, ArrayList<String> contactPhoneNO) {
    this.contactId = contactId;
    this.contactName = contactName;
    this.contactNamePinYin = contactNamePinYin;
    this.photoId = photoId;
    this.hasPhoneNumber = hasPhoneNumber;
    this.contactPhoneNO = contactPhoneNO;
  }

  public void setSpellWordsList(List<String> l) {
    spellWordsList = l;
  }

  public boolean isContainSearchName(String name) {
    if (spellWordsList != null && spellWordsList.contains(name)) {
      return true;
    }
    return false;
  }

  public int getContactId() {
    return contactId;
  }

  public String getContactName() {
    return contactName;
  }

  public String getContactNamePinYin() {
    return contactNamePinYin;
  }

  public int getPhotoId() {
    return photoId;
  }

  public int getHasPhoneNumber() {
    return hasPhoneNumber;
  }

  public ArrayList<String> getContactPhoneNO() {
    return contactPhoneNO;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public void setContactNamePinYin(String contactNamePinYin) {
    this.contactNamePinYin = contactNamePinYin;
  }

  public void setPhotoId(int photoId) {
    this.photoId = photoId;
  }

  public void setHasPhoneNumber(int hasPhoneNumber) {
    this.hasPhoneNumber = hasPhoneNumber;
  }

  public void setContactPhoneNO(ArrayList<String> contactPhoneNO) {
    this.contactPhoneNO = contactPhoneNO;
  }

  public void cleanPhone() {
    this.contactPhoneNO.clear();
  }
}
