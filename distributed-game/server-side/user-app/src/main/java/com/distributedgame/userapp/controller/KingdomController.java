package com.distributedgame.userapp.controller;

import com.distributedgame.dataaccess.model.viewmodel.KingdomViewModel;
import com.distributedgame.dataaccess.service.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KingdomController {
    private KingdomService service;

    @Autowired
    public KingdomController(KingdomService service) {
        this.service = service;
    }

    @PostMapping("/kingdom/create-for-user/{userId}")
    public List<KingdomViewModel> createKingdomsForUser(@PathVariable long userId) {
        return this.service.createForUser(userId);
    }
}
