package com.java_dapps.store.controller;

import com.java_dapps.store.model.viewModel.ItemViewModel;
import com.java_dapps.store.service.ItemService;
import com.topchain.node.entity.Node;
import com.topchain.node.model.bindingModel.NotifyBlockModel;
import com.topchain.node.model.viewModel.BlockViewModel;
import com.topchain.node.model.viewModel.NodeInfoViewModel;
import com.topchain.node.model.viewModel.ResponseMessageViewModel;
import com.topchain.node.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.topchain.node.util.NodeUtils.notifyPeersForNewBlock;

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

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemViewModel> getBlockByIndex(@PathVariable long id) {
        ItemViewModel itemViewModel = this.itemService.getItem(id);

        if (!itemViewModel.isExists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(itemViewModel, HttpStatus.OK);
    }

    @PostMapping("/item/purchase")
    public ResponseMessageViewModel purchaseItem(/* TODO: 26/03/18 id */ ) {
        return new ResponseMessageViewModel("Thank you for the notification.");
    }
}
