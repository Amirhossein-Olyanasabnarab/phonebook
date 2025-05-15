package com.dev.phonebook.service;

import com.dev.phonebook.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneBookService {
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
}
