package com.distributedgame.userapp.controller.controller;

import com.distributedgame.dataaccess.service.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemController {
    private KingdomService itemService;

    @Autowired
    public ItemController(KingdomService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/get")
    public String getItems() {
        return this.itemService.getMessage();
    }
}
