package com.dev.phonebook.service;

import com.dev.phonebook.config.GreetingConfig;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    private final GreetingConfig greetingConfig;
    public GreetingService(GreetingConfig greetingConfig) {
        this.greetingConfig = greetingConfig;
    }

    public String welcomeMessage() {
        return greetingConfig.getMessage();
    }
}
