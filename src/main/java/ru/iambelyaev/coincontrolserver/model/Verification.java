package ru.iambelyaev.coincontrolserver.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table (name = "verifications")
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private Date creation_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private int expectedMoney;

    private int actualMoney;

    private int difference;

    public Verification() {
        this.creation_time = new Date(System.currentTimeMillis());;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public int getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(int actualMoney) {
        this.actualMoney = actualMoney;
    }

    public int getExpectedMoney() {
        return expectedMoney;
    }

    public void setExpectedMoney(int expectedMoney) {
        this.expectedMoney = expectedMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }
}
