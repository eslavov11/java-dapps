package com.java_dapps.store.service;

import com.java_dapps.store.entity.Customer;
import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;

public interface CustomerService {
    CustomerViewModel get(long id);

    Customer getByUserId(long userId);

    CustomerViewModel getViewModelByUserId(long userId);

    void create(CustomerRegisterModel customerRegisterModel);
}
