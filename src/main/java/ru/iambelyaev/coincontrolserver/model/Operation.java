package ru.iambelyaev.coincontrolserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table (name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id")
    private Wallet from_wallet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id")
    private Wallet to_wallet_id;

    @NotNull
    @DecimalMin("1")
    private int money;

    @JsonIgnore
    private Date creation_time;

    @Column(name = "operation_time")
    private Date time;

    private boolean is_processed;

    @Enumerated(EnumType.ORDINAL)
    private OperatationType operatationType;

    public Operation() {
        this.creation_time = new Date(System.currentTimeMillis());
    }

    public Operation(int money, Date time, OperatationType operatationType) {
        this.money = money;
        this.time = time;
        this.operatationType = operatationType;
    }

    public enum OperatationType {
        MAKE, SPEND, TRANSFER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet getFrom_wallet_id() {
        return from_wallet_id;
    }

    public void setFrom_wallet_id(Wallet from_wallet_id) {
        this.from_wallet_id = from_wallet_id;
    }

    public Wallet getTo_wallet_id() {
        return to_wallet_id;
    }

    public void setTo_wallet_id(Wallet to_wallet_id) {
        this.to_wallet_id = to_wallet_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public OperatationType getOperatationType() {
        return operatationType;
    }

    public void setOperatationType(OperatationType operatationType) {
        this.operatationType = operatationType;
    }

    public boolean isIs_processed() {
        return is_processed;
    }

    public void setIs_processed(boolean is_processed) {
        this.is_processed = is_processed;
    }

}