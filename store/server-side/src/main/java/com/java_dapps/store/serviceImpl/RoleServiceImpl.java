package com.java_dapps.store.serviceImpl;

import com.java_dapps.store.entity.Role;
import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;
import com.java_dapps.store.repository.CustomerRepository;
import com.java_dapps.store.repository.RoleRepository;
import com.java_dapps.store.service.CustomerService;
import com.java_dapps.store.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return this.roleRepository.findOneByAuthority(authority);
    }
}
