package com.distributedgame.userapp.controller;

import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;
import com.distributedgame.dataaccess.service.UserService;
import com.distributedgame.userapp.service.WebGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import static com.distributedgame.userapp.util.Utils.serializeJSON;

@RestController
public class UserController {
    private UserService service;
    private WebGameService webGameService;
    private SimpMessagingTemplate template;

    @Autowired
    public UserController(UserService service,
                          SimpMessagingTemplate template,
                          WebGameService webGameService) {
        this.service = service;
        this.template = template;
        this.webGameService = webGameService;
    }


    @MessageMapping("/register")
    @SendTo("/topic/profile")
    public UserViewModel greeting(UserRegisterModel userRegisterModel) throws Exception {
        UserViewModel userViewModel = this.service.register(userRegisterModel);

        new Thread(new GameModuleConnectRunnable(userViewModel.getId())).start();

        return userViewModel;
    }

    private class GameModuleConnectRunnable implements Runnable {
        long userId;

        public GameModuleConnectRunnable(long userId) {
            this.userId = userId;
        }

        public void run() {
            UserController.this.webGameService.createKingdomsForUser(userId);

            UserViewModel userViewModel = UserController.this.service.getViewModelById(userId);

            UserController.this.template
                    .convertAndSend("/topic/profile",
                            serializeJSON(userViewModel, false));
        }
    }
}