package com.java_dapps.store.controller;

import com.java_dapps.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    // TODO: 29-Mar-18
    /*@GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }*/
}
