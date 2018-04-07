package com.distributedgame.userapp.controller;

import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;
import com.distributedgame.dataaccess.service.UserService;
import com.distributedgame.userapp.service.WebGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService service;
    private WebGameService webGameService;

    @Autowired
    public UserController(UserService service,
                          WebGameService webGameService) {
        this.service = service;
        this.webGameService = webGameService;
    }

    @MessageMapping("/register")
    @SendTo("/topic/profile")
    public UserViewModel greeting(UserRegisterModel userRegisterModel) throws Exception {
        UserViewModel userViewModel = this.service.register(userRegisterModel);

        this.webGameService.createKingdomsForUser(userViewModel);

        return userViewModel;
    }
}