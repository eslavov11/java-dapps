package com.java_dapps.store.service;

import com.java_dapps.store.entity.Item;
import com.java_dapps.store.model.bindingModel.ItemModel;
import com.java_dapps.store.model.viewModel.ItemViewModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ItemService {
    List<ItemViewModel> getAll();

    List<ItemViewModel> getAllForPurchase();

    ItemViewModel get(long id);

    void buyItem(long id, long userId);

    void create(ItemModel itemModel);
}
