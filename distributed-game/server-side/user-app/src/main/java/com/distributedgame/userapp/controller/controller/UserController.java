package com.distributedgame.userapp.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private SimpMessagingTemplate template;

    @Autowired
    public UserController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay

        long userId = 333;
        new Thread(new GameModuleConnectRunnable(userId)).start();

        return new Greeting("Hello, " + message.getName() + "!");
    }

    private class GameModuleConnectRunnable implements Runnable {
        long userId;

        public GameModuleConnectRunnable(long userId) {
            this.userId = userId;
        }

        public void run() {
            try {
                Thread.sleep(3000); // simulated delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            UserController.this.template
                    .convertAndSend("/topic/greetings", "Created kingdoms for user: " + this.userId);
        }
    }
}
