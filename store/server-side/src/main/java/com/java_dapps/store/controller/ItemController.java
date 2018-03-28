package com.java_dapps.store.controller;

import com.java_dapps.store.model.bindingModel.ItemModel;
import com.java_dapps.store.model.viewModel.ItemViewModel;
import com.java_dapps.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemViewModel> getItems() {
        return this.itemService.getAll();
    }

    @GetMapping("/items-for-purchase")
    public List<ItemViewModel> getItemsForPurchase() {
        return this.itemService.getAllForPurchase();
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemViewModel> getItem(@PathVariable long id) {
        ItemViewModel itemViewModel = this.itemService.get(id);

        if (itemViewModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(itemViewModel, HttpStatus.OK);
    }

    @PostMapping("/item/add")
    public void addItem(ItemModel itemModel) {
        this.itemService.create(itemModel);
    }

    @PostMapping("/item/{id}/buy")
    public void buyItem(@PathVariable long id) {
        this.itemService.buyItem(id);
    }
}
