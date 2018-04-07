package com.distributedgame.gameapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.*")
public class GameApp {
    public static void main(String[] args) {
        SpringApplication.run(GameApp.class, args);
    }
}
