package org.uncle.lee.simpledatasource.Entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CONTACT".
 */
@Entity
public class Contact {

    @Id
    private Long id;

    @NotNull
    private String name;
    private String py;
    private String number;

    @Generated
    public Contact() {
    }

    public Contact(Long id) {
        this.id = id;
    }

    @Generated
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

    @NotNull
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(@NotNull String name) {
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

}
