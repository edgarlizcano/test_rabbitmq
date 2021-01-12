package com.example.testa.config;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public void receiveMessage(String message) {
        System.out.println("Received on A <" + message + ">");
    }

}
