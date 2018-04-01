package com.distributedgame.dataaccess.model.viewmodel;

import java.util.List;

public class UserViewModel {
    private String username;
    private List<KingdomViewModel> kingdoms;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<KingdomViewModel> getKingdoms() {
        return kingdoms;
    }

    public void setKingdoms(List<KingdomViewModel> kingdoms) {
        this.kingdoms = kingdoms;
    }
}
