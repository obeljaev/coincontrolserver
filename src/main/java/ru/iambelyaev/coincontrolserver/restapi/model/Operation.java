package ru.iambelyaev.coincontrolserver.restapi.model;

import java.time.LocalDateTime;

public class Operation {
    private Integer user = 0;
    private Integer category = 0;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime operationTime = LocalDateTime.now();
    private Integer type = 0;
    private Integer fromWallet = 0;
    private Integer toWallet = 0;
    private Integer money = 0;

    public Operation(int user, int category,
                     LocalDateTime creationTime, LocalDateTime operationTime,
                     int type, int fromWallet, int toWallet, int money) {
        this.user = user;
        this.category = category;
        this.creationTime = creationTime;
        this.operationTime = operationTime;
        this.type = type;
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.money = money;
    }

    public Operation() {
    }

    public int getUser() {
        return user;
    }
    public void setUser(Integer user) {
        this.user = user;
    }

    public int getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(LocalDateTime creation_time) {
        this.creationTime = creation_time;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }
    public void setOperationTime(LocalDateTime operation_time) {
        this.operationTime = operation_time;
    }

    public int getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public int getFromWallet() {
        return fromWallet;
    }
    public void setFromWallet(Integer fromWallet) {
        this.fromWallet = fromWallet;
    }

    public int getToWallet() {
        return toWallet;
    }
    public void setToWallet(Integer toWallet) {
        this.toWallet = toWallet;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
}