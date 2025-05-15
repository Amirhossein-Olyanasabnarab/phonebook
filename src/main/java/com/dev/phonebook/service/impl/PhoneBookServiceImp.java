package com.dev.phonebook.service.impl;

import com.dev.phonebook.entity.BusinessContact;
import com.dev.phonebook.entity.Contact;
import com.dev.phonebook.service.PhoneBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneBookServiceImp implements PhoneBookService {
    private static final List<Contact> contacts = new ArrayList<Contact>();


    public void add(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getActiveContacts() {
        return contacts.stream()
                .filter(contact -> !contact.getDeleted())
                .collect(Collectors.toList());
    }

    public List<Contact> getContactsByName(String name) {
        return contacts.stream()
                .filter(contact -> !contact.getDeleted())
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Contact> getContactsByFax(String fax) {
        return contacts.stream()
                .filter(contact -> !contact.getDeleted())
                .filter(contact -> contact instanceof BusinessContact )
                .map(contact -> (BusinessContact) contact)
                .filter(businessContact -> businessContact.getFax().equals(fax))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContact(int id) {
        contacts.stream()
                .filter(contact -> !contact.getDeleted())
                .filter(contact -> contact.getId() == id)
                .forEach(contact -> contact.setDeleted(true));
    }

    @Override
    public List<Contact> getDeletedContacts() {
        return contacts.stream()
                .filter(contact -> contact.getDeleted())
                .collect(Collectors.toList());
    }
}
