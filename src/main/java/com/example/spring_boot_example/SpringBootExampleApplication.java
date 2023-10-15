package com.example.spring_boot_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import java.util.Date;

@SpringBootApplication
public class SpringBootExampleApplication {

    public static void main(String[] args) {
//
        var springApp = new SpringApplication(SpringBootExampleApplication.class);
        springApp.addListeners((ApplicationListener<ApplicationStartingEvent>) event -> {
            System.out.println("Application Starting Event logged at " + new Date(event.getTimestamp()));
        });
        springApp.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
    }
}
