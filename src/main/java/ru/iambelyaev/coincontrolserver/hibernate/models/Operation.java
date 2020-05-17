package ru.iambelyaev.coincontrolserver.hibernate.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    private LocalDateTime creation_time;

    private LocalDateTime operation_time;

    private int type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id")
    private Wallet from_wallet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id")
    private Wallet to_wallet_id;

    private int money;

    private boolean is_processed;

    public Operation() {
    }

    public Operation(LocalDateTime creation_time, LocalDateTime operation_time,
                     int type, int money, boolean is_processed) {
        this.creation_time = creation_time;
        this.operation_time = operation_time;
        this.type = type;
        this.money = money;
        this.is_processed = is_processed;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }
    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOperationTime() {
        return operation_time;
    }
    public void setOperationTime(LocalDateTime time) {
        this.operation_time = time;
    }

    public LocalDateTime getCreationTime() {
        return creation_time;
    }
    public void setCreationTime(LocalDateTime time) {
        this.creation_time = time;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public Wallet getFrom_wallet_id() {
        return from_wallet_id;
    }
    public void setFrom_wallet_id(Wallet wallet) {
        this.from_wallet_id = wallet;
    }

    public Wallet getTo_wallet_id() {
        return to_wallet_id;
    }
    public void setTo_wallet_id(Wallet wallet) {
        this.to_wallet_id = wallet;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public boolean getIsProcessed() {
        return is_processed;
    }
    public void setIsProcessed(boolean is_processed) {
        this.is_processed = is_processed;
    }

    @Override
    public String toString() {
        return id + " " + type + " not dev";
    }
}
