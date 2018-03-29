package com.java_dapps.store.serviceImpl;

import com.java_dapps.store.entity.Customer;
import com.java_dapps.store.entity.User;
import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.bindingModel.UserRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;
import com.java_dapps.store.repository.CustomerRepository;
import com.java_dapps.store.service.CustomerService;
import com.java_dapps.store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public CustomerServiceImpl(ModelMapper modelMapper,
                               CustomerRepository customerRepository,
                               UserService userService) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    @Override
    public CustomerViewModel get(long id) {
        Customer customer = this.customerRepository.getOne(id);

        return this.modelMapper.map(customer, CustomerViewModel.class);
    }

    @Override
    public CustomerViewModel getByUserId(long userId) {
        Customer customer = this.customerRepository.findOneByUserId(userId);

        return this.modelMapper.map(customer, CustomerViewModel.class);
    }

    @Override
    public void create(CustomerRegisterModel customerRegisterModel) {
        UserRegisterModel userRegisterModel = this.modelMapper.map(customerRegisterModel, UserRegisterModel.class);
        String DEFAULT_PATIENT_ROLE = "ROLE_CUSTOMER";
        userRegisterModel.setAdditionalRole(DEFAULT_PATIENT_ROLE);
        User user = this.userService.register(userRegisterModel);

        Customer customer = this.modelMapper.map(customerRegisterModel, Customer.class);
        customer.setUser(user);

        this.customerRepository.saveAndFlush(customer);
    }
}