package com.java_dapps.store.controller;

import com.java_dapps.store.entity.User;
import com.java_dapps.store.model.bindingModel.CustomerRegisterModel;
import com.java_dapps.store.model.viewModel.CustomerViewModel;
import com.java_dapps.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/register")
    public void addItem(@RequestBody CustomerRegisterModel customerRegisterModel) {
        this.customerService.create(customerRegisterModel);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerViewModel> get(Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        CustomerViewModel customerViewModel = this.customerService.getByUserId(userId);

        if (customerViewModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerViewModel, HttpStatus.OK);
    }
}
