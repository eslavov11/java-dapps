package com.java_dapps.store.serviceImpl;

import com.java_dapps.store.entity.Role;
import com.java_dapps.store.entity.User;
import com.java_dapps.store.model.bindingModel.UserRegisterModel;
import com.java_dapps.store.repository.RoleRepository;
import com.java_dapps.store.repository.UserRepository;
import com.java_dapps.store.service.RoleService;
import com.java_dapps.store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public User register(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(userRegisterModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());

        // Additional role
        if (userRegisterModel.getAdditionalRole() != null) {
            Role additionalRole = this.roleService.getRoleByAuthority(userRegisterModel.getAdditionalRole());
            user.addRole(additionalRole);
        }

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);

        return user;
    }
}
