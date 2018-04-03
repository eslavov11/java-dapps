package com.distributedgame.dataaccess.serviceimpl;

import com.distributedgame.dataaccess.entity.User;
import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;
import com.distributedgame.dataaccess.repository.UserRepository;
import com.distributedgame.dataaccess.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository,
                           ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserViewModel register(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);

        User createdUser = this.repository.save(user);
        return this.modelMapper.map(createdUser, UserViewModel.class);
    }

    @Override
    public User getById(long userId) {
        return this.repository.getOne(userId);
    }

    @Override
    public UserViewModel getViewModelById(long userId) {
        UserViewModel userViewModel = new UserViewModel();
        User user = this.getById(userId);
        userViewModel.setId(user.getId());
        userViewModel.setUsername(user.getUsername());

        return userViewModel;
    }
}
