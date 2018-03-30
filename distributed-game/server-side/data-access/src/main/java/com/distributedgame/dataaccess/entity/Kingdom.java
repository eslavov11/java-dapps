package com.distributedgame.dataaccess.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kingdoms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"xCoord", "yCoord"})
        },
        indexes = { @Index(name = "IDX_COORDS", columnList = "xCoord,yCoord") })
public class Kingdom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int xCoord;

    private int yCoord;

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

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
