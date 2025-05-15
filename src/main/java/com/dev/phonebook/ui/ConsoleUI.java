package com.dev.phonebook.ui;

import com.dev.phonebook.entity.BusinessContact;
import com.dev.phonebook.entity.Contact;
import com.dev.phonebook.entity.PersonalContact;
import com.dev.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI implements AutoCloseable {

    private Scanner scanner = new Scanner(System.in);

    private final PhoneBookService phoneBookService;

    @Autowired
    public ConsoleUI(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }


    public void startApp() {
        int choice;

        do {
            printMenu();
            System.out.println("Choose your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("Goodbye");
                    break;
                case 1:
                    addNewContact();
                    break;
                case 2:
                    showAllContacts();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 0);
    }

    private void showAllContacts() {
        List<Contact> activeContacts = phoneBookService.getActiveContacts();
        if (!activeContacts.isEmpty()) {
            for (Contact contact : activeContacts) {
                System.out.println(contact);
            }
        }else{
            System.out.println("No active contacts");
        }
    }

    private void addNewContact() {
        System.out.println("Please choose your contact type: ");
        System.out.println("1. Personal Contact");
        System.out.println("2. Business Contact");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Please enter your contact name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter your contact family name: ");
            String family = scanner.nextLine();
            System.out.println("Please enter your contact phone number: ");
            String phone = scanner.nextLine();
            System.out.println("Please enter your contact email: ");
            String email = scanner.nextLine();
            PersonalContact personalContact = new PersonalContact(name, family, phone);
            personalContact.setEmail(email);
            phoneBookService.add(personalContact);
        } else if (choice == 2) {
            System.out.println("Please enter your contact name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter your contact family name: ");
            String family = scanner.nextLine();
            System.out.println("Please enter your contact phone number: ");
            String phone = scanner.nextLine();
            System.out.println("Please enter your contact fax: ");
            String fax = scanner.nextLine();
            BusinessContact businessContact = new BusinessContact(name, family, phone);
            businessContact.setFax(fax);
            phoneBookService.add(businessContact);
        } else {
            System.out.println("Invalid choice............");
        }
    }


    private void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Show all contacts");
        System.out.println("2. show contacts by Id");
        System.out.println("3. show contacts by Name");
        System.out.println("4. show contacts by Phone Number");
        System.out.println("5. Update contact by Id");
        System.out.println("6. Delete contact by Id");
        System.out.println("7. Show all deleted contacts");
    }

    @Override
    public void close() {
        scanner.close();
    }
}
