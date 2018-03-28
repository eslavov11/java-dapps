//package com.distributedgame.userapp.controller;
//
//import com.distributedgame.dataaccess.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class ItemController {
//    private ItemService itemService;
//
//    @Autowired
//    public ItemController(ItemService itemService) {
//        this.itemService = itemService;
//    }
//
//    @GetMapping("/get")
//    public String getItems() {
//        return this.itemService.getMessage();
//    }
//}
