package com.distributedgame.dataaccess.service;

import com.distributedgame.dataaccess.entity.User;
import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;

public interface UserService {
    UserViewModel register(UserRegisterModel userRegisterModel);

    User getById(long userId);

    UserViewModel getViewModelById(long userId);
}
