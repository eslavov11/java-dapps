package com.distributedgame.userapp.controller;

import com.distributedgame.dataaccess.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private ItemService itemService;

    @Autowired
    public UserController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/user")
    public String getItems() {
        return this.itemService.getMessage() + "user!!!!";
    }
}
