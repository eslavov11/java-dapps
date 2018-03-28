package com.distributedgame.dataaccess.serviceImpl;

import com.distributedgame.dataaccess.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String getMessage() {
        return "Test from dataaccess";
    }
}
