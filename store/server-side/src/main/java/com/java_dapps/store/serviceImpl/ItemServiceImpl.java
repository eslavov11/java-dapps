package com.java_dapps.store.serviceImpl;

import com.java_dapps.store.entity.Customer;
import com.java_dapps.store.entity.Item;
import com.java_dapps.store.model.bindingModel.ItemModel;
import com.java_dapps.store.model.viewModel.ItemViewModel;
import com.java_dapps.store.repository.ItemRepository;
import com.java_dapps.store.service.CustomerService;
import com.java_dapps.store.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private CustomerService customerService;
    private ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ModelMapper modelMapper,
                           ItemRepository itemRepository,
                           CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
        this.customerService = customerService;
    }

    @Override
    public List<ItemViewModel> getAll() {
        List<ItemViewModel> itemViewModels = new ArrayList<>();
        this.itemRepository.findAll().forEach((Item item) -> {
            ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
            itemViewModels.add(itemViewModel);
        });

        return itemViewModels;
    }

    @Override
    public List<ItemViewModel> getAllForPurchase() {
        List<ItemViewModel> itemViewModels = new ArrayList<>();
        this.itemRepository.findAllBySoldFalse().forEach((Item item) -> {
            ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
            itemViewModels.add(itemViewModel);
        });

        return itemViewModels;
    }

    @Override
    public ItemViewModel get(long id) {
        Item item = this.itemRepository.getOne(id);

        return this.modelMapper.map(item, ItemViewModel.class);
    }

    @Override
    public void buyItem(long id, long userId) {
        Item item = this.itemRepository.getOne(id);
        Customer customer = this.customerService.getByUserId(userId);

        item.setCustomer(customer);

        this.itemRepository.saveAndFlush(item);
    }

    @Override
    public void create(ItemModel itemModel) {
        Item item = this.modelMapper.map(itemModel, Item.class);

        this.itemRepository.saveAndFlush(item);
    }
}
