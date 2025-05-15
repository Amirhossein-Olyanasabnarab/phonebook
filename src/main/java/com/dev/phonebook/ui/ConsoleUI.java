package com.dev.phonebook.ui;

import com.dev.phonebook.config.GreetingConfig;
import com.dev.phonebook.entity.BusinessContact;
import com.dev.phonebook.entity.Contact;
import com.dev.phonebook.entity.PersonalContact;
import com.dev.phonebook.service.GreetingService;
import com.dev.phonebook.service.impl.PhoneBookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI implements AutoCloseable {

    private Scanner scanner = new Scanner(System.in);

    private final PhoneBookServiceImp phoneBookServiceImp;
    private final GreetingService greetingService;
    @Autowired
    public ConsoleUI(PhoneBookServiceImp phoneBookServiceImp, GreetingService greetingService) {
        this.phoneBookServiceImp = phoneBookServiceImp;
        this.greetingService = greetingService;
    }


    public void startApp() {
        int choice;
        System.out.println(greetingService.welcomeMessage());
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
                case 3:
                    showContactsByName();
                    break;
                case 4:
                    showContactsByFax();
                    break;
                case 6:
                    deleteContactById();
                    break;
                    case 7:
                        showAllDeletedContacts();
                        break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 0);
    }

    private void showAllDeletedContacts() {
        List<Contact> getActiveContacts = phoneBookServiceImp.getActiveContacts();
        if (!getActiveContacts.isEmpty()) {
            List<Contact> deletedContacts = phoneBookServiceImp.getDeletedContacts();
            if (!deletedContacts.isEmpty()) {
                for (Contact contact : deletedContacts) {
                    System.out.println(contact);
                }
            }else {
                System.out.println("No active contacts found");
            }
        }else {
            System.out.println("No active contacts");
        }
    }


    private void deleteContactById() {
        List<Contact> getActiveContacts = phoneBookServiceImp.getActiveContacts();
        if(!getActiveContacts.isEmpty()) {
            System.out.println("Please enter your ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            phoneBookServiceImp.deleteContact(id);
            System.out.println("Contact deleted successfully");
        }else {
            System.out.println("No active contacts found");
        }
    }

    private void showContactsByFax() {
        List<Contact> getActiveContacts = phoneBookServiceImp.getActiveContacts();
        if (!getActiveContacts.isEmpty()) {
            System.out.println("Please enter your fax number: ");
            String fax = scanner.nextLine();
            List<Contact> contactsByFax = phoneBookServiceImp.getContactsByFax(fax);
            if (!contactsByFax.isEmpty()) {
                for (Contact contact : contactsByFax) {
                    System.out.println(contact);
                }
            } else {
                System.out.println("No contact found");
            }
        } else {
            System.out.println("No active contacts");
        }
    }

    private void showContactsByName() {
        List<Contact> activeContacts = phoneBookServiceImp.getActiveContacts();
        if (!activeContacts.isEmpty()) {
            System.out.println("Enter contact name: ");
            String name = scanner.nextLine();
            List<Contact> contacts = phoneBookServiceImp.getContactsByName(name);
            if (!contacts.isEmpty()) {
                for (Contact contact : contacts) {
                    System.out.println(contact);
                }
            } else {
                System.out.println("Contact not found");
            }
        } else {
            System.out.println("No active contacts");
        }
    }

    private void showAllContacts() {
        List<Contact> activeContacts = phoneBookServiceImp.getActiveContacts();
        if (!activeContacts.isEmpty()) {
            for (Contact contact : activeContacts) {
                System.out.println(contact);
            }
        } else {
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
            phoneBookServiceImp.add(personalContact);
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
            phoneBookServiceImp.add(businessContact);
        } else {
            System.out.println("Invalid choice............");
        }
    }


    private void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Show all contacts");
        System.out.println("2. show contacts by Id");
        System.out.println("3. show contacts by Name");
        System.out.println("4. show contacts by Fax Number");
        System.out.println("5. Update contact by Id");
        System.out.println("6. Delete contact by Id");
        System.out.println("7. Show all deleted contacts");
    }

    @Override
    public void close() {
        scanner.close();
    }
}
