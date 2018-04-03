package com.distributedgame.dataaccess.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kingdoms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"XCoord", "YCoord"})
        })
public class Kingdom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int XCoord;

    private int YCoord;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Kingdom() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getXCoord() {
        return XCoord;
    }

    public void setXCoord(int XCoord) {
        this.XCoord = XCoord;
    }

    public int getYCoord() {
        return YCoord;
    }

    public void setYCoord(int YCoord) {
        this.YCoord = YCoord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
