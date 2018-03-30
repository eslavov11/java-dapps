package com.java_dapps.store.controller;

import com.java_dapps.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


//@CrossOrigin(origins="*", maxAge=3600)
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public Principal user(Principal principal) {
        return principal;
    }
}
