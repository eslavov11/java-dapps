package com.java_dapps.store.model.viewModel;

public class ItemViewModel {
    private int confirmations;
    private long balance;

    public ItemViewModel() {
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
