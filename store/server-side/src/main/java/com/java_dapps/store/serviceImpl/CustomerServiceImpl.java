package com.java_dapps.store.serviceImpl;

import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;
import com.java_dapps.store.repository.CustomerRepository;
import com.java_dapps.store.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerViewModel get(long id) {
        return null;
    }

    @Override
    public void create(CustomerRegisterModel customerRegisterModel) {

    }
}
