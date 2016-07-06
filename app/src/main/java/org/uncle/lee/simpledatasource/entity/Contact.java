package org.uncle.lee.simpledatasource.entity;

/**
 * Created by Austin on 2016/7/6.
 */
public class Contact {
  private Long id;

  private String name;
  private String number;
  private String py;
  private long lastTimeContacted;

  public Contact() {
  }

  public Contact(Long id, String name, String py, String number) {
    this.id = id;
    this.name = name;
    this.py = py;
    this.number = number;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPy() {
    return py;
  }

  public void setPy(String py) {
    this.py = py;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public long getLastTimeContacted() {
    return lastTimeContacted;
  }

  public void setLastTimeContacted(long lastTimeContacted) {
    this.lastTimeContacted = lastTimeContacted;
  }

  @Override public String toString() {
    return "Contact{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", number='" + number + '\'' +
        ", py='" + py + '\'' +
        ", lastTimeContacted=" + lastTimeContacted +
        '}';
  }
}
