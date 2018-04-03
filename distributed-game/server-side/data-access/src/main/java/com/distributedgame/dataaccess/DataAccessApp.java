package com.distributedgame.dataaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by eslavov on 03-Apr-18.
 */
@SpringBootApplication(scanBasePackages = "com.distributedgame.dataaccess")
@PropertySource(value = "data.properties")
public class DataAccessApp {
    public static void main(String[] args) {
        SpringApplication.run(DataAccessApp.class, args);
    }
}
