package com.distributedgame.userapp.controller;

import com.distributedgame.dataaccess.service.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {
    private KingdomService service;

    @Autowired
    public KingdomController(KingdomService service) {
        this.service = service;
    }

    @PostMapping("/kingdom/create-for-user/{userId}")
    public void createKingdomsForUser(@PathVariable long userId) {
        for (int i = 0; i < 10; i++) {
            this.service.createForUser(userId);
        }
    }
}
