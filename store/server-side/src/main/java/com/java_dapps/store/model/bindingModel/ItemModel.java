package com.java_dapps.store.model.bindingModel;

public class ItemModel {
    private String description;
    private long price;

    public ItemModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
