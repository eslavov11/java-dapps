package com.distributedgame.dataaccess.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "kingdom")
    private Set<Kingdom> kingdoms;

    public User() {
        this.setKingdoms(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Kingdom> getKingdoms() {
        return kingdoms;
    }

    public void setKingdoms(Set<Kingdom> kingdoms) {
        this.kingdoms = kingdoms;
    }
}
