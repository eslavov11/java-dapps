package com.java_dapps.store.model.viewModel;

import java.util.List;

public class CustomerViewModel {
    private String username;
    private String keystoreJson;
    private List<ItemViewModel> items;

    public CustomerViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeystoreJson() {
        return keystoreJson;
    }

    public void setKeystoreJson(String keystoreJson) {
        this.keystoreJson = keystoreJson;
    }

    public List<ItemViewModel> getItems() {
        return items;
    }

    public void setItems(List<ItemViewModel> items) {
        this.items = items;
    }
}
