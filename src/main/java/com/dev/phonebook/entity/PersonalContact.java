package com.dev.phonebook.entity;

import com.dev.phonebook.enums.ContactType;

public class PersonalContact extends Contact {


    private String email;

    public PersonalContact(String name, String family, String phone) {
        super(name, family, phone, ContactType.PERSONAL_CONTACT);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonalContact{ " +
                super.toString() +
                " email='" + email + '\'' +
                '}';
    }
}
