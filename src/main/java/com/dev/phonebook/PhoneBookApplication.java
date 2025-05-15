package com.dev.phonebook;

import com.dev.phonebook.ui.ConsoleUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookApplication implements CommandLineRunner {

    private final ConsoleUI consoleUI;
    public PhoneBookApplication(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }


    @Override
    public void run(String... args) throws Exception {
        consoleUI.startApp();
    }

    public static void main(String[] args) {
        SpringApplication.run(PhoneBookApplication.class, args);
    }

}
