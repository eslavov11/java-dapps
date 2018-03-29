package com.distributedgame.gameapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class GameApp {
    public static void main(String[] args) {
        SpringApplication.run(GameApp.class, args);
    }
}
