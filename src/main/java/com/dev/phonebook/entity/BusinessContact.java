package com.dev.phonebook.entity;

import com.dev.phonebook.enums.ContactType;

public class BusinessContact extends Contact{

    private String fax;

    public BusinessContact(String name, String family, String phone) {
        super(name, family, phone, ContactType.BUSINESS_CONTACT);
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "BusinessContact{ " +
                super.toString() +
                " fax='" + fax + '\'' +
                '}';
    }
}
