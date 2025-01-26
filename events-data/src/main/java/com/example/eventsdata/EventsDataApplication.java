package com.example.eventsdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class EventsDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsDataApplication.class, args);
    }

}
