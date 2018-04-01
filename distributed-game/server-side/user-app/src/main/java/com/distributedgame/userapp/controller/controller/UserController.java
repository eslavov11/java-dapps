package com.distributedgame.userapp.controller.controller;

import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;
import com.distributedgame.dataaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import static com.distributedgame.userapp.controller.util.Utils.serializeJSON;

@RestController
public class UserController {
    private UserService service;
    private SimpMessagingTemplate template;

    @Autowired
    public UserController(UserService service,
                          SimpMessagingTemplate template) {
        this.service = service;
        this.template = template;
    }


    @MessageMapping("/user/register")
    @SendTo("/user/registration")
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
            // TODO: 01/04/18 microservices

            UserViewModel userViewModel = UserController.this.service.getViewModelById(userId);

            UserController.this.template
                    .convertAndSend("/user/registration",
                            serializeJSON(userViewModel, false));
        }
    }
}
