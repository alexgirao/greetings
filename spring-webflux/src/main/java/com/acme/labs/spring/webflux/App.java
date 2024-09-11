package com.acme.labs.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.setProperty("reactor.schedulers.defaultBoundedElasticSize", "1000");
        SpringApplication.run(App.class, args);
    }
}
