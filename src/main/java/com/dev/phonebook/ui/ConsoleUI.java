package com.dev.phonebook.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI implements AutoCloseable {

    private Scanner scanner = new Scanner(System.in);

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
                    System.out.println("add");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while (choice != 0);
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
