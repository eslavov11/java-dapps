package com.distributedgame.dataaccess.service;

import com.distributedgame.dataaccess.entity.User;
import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;

public interface UserService {
    User register(UserRegisterModel userRegisterModel);

    User getById(long userId);
}
