package com.dev.phonebook.service;

import com.dev.phonebook.entity.Contact;

import java.util.List;

public interface PhoneBookService {
    void add(Contact contact);
    List<Contact> getActiveContacts();
    List<Contact> getContactsByName(String name);
    List<Contact> getContactsByFax(String fax);
    void deleteContact(int id);
    List<Contact> getDeletedContacts();
}
