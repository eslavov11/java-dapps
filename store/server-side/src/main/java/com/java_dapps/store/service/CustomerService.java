package com.java_dapps.store.service;

import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;

public interface CustomerService {
    CustomerViewModel get(long id);

    void create(CustomerRegisterModel customerRegisterModel);
}
