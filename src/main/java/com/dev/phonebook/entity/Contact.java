package com.dev.phonebook.entity;

import com.dev.phonebook.enums.ContactType;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Contact {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private Integer id;
    private String name;
    private String family;
    private String phone;
    private final ContactType contactType;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = capitalizeFirstLetter(name);
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = capitalizeFirstLetter(family);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Contact(String name, String family, String phone, ContactType contactType) {
        this.id = counter.getAndIncrement();
        this.name = capitalizeFirstLetter(name);
        this.family = capitalizeFirstLetter(family);
        this.phone = phone;
        this.contactType = contactType;
        this.deleted = false;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", phone='" + phone + '\'' +
                ", deleted=" + deleted ;
    }


    //region method

    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    //endregion
}
