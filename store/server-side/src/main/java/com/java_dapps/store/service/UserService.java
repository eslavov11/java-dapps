package com.java_dapps.store.service;

import com.java_dapps.store.entity.User;
import com.java_dapps.store.model.bindingModel.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(UserRegisterModel userRegisterModel);
}
