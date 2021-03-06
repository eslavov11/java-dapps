package com.java_dapps.store.controller;

import com.java_dapps.store.entity.User;
import com.java_dapps.store.model.bindingModel.ItemModel;
import com.java_dapps.store.model.viewModel.ItemViewModel;
import com.java_dapps.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public List<ItemViewModel> getItems() {
        return this.itemService.getAll();
    }

    @GetMapping("/item/for-sale")
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
    @PreAuthorize("hasRole('USER')")
    public void addItem(@RequestBody ItemModel itemModel) {
        this.itemService.create(itemModel);
    }

    @PostMapping("/item/{id}/buy")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void buyItem(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.itemService.buyItem(id, user.getId());
    }
}
