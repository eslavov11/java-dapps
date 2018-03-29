package com.java_dapps.store.controller;

import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;
import com.java_dapps.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/register")
    public void addItem(CustomerRegisterModel customerRegisterModel) {
        this.customerService.create(customerRegisterModel);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerViewModel> get() {
        // TODO: 29-Mar-18 get principal
        long customerId = 1;
        CustomerViewModel customerViewModel = this.customerService.get(customerId);

        if (customerViewModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerViewModel, HttpStatus.OK);
    }
}
