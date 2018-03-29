package com.java_dapps.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class Store {
    public static void main(String[] args) {
        SpringApplication.run(Store.class, args);
    }
}
