package com.distributedgame.userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableEurekaServer
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com"})
@ComponentScan({"com.distributedgame.dataaccess.repository"})
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }
}
