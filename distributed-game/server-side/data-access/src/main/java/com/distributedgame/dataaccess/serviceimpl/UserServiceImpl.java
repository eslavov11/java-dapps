package com.distributedgame.dataaccess.serviceimpl;

import com.distributedgame.dataaccess.entity.User;
import com.distributedgame.dataaccess.model.bindingmodel.UserRegisterModel;
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
    public User register(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);

        return this.repository.saveAndFlush(user);
    }

    @Override
    public User getById(long userId) {
        return this.repository.getOne(userId);
    }
}
