package com.distributedgame.gameapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@EnableEurekaClient
@Configuration
@ComponentScan(basePackages = "com.*")
@SpringBootApplication
public class GameApp {
    public static void main(String[] args) {
        SpringApplication.run(GameApp.class, args);
    }
}
