package ru.iambelyaev.coincontrolserver.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table (name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please enter name")
    private String name;

    //@NotEmpty(message = "Please enter money")
    private Integer money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "from_wallet_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(
            action = org.hibernate.annotations.OnDeleteAction.CASCADE
    )
    @JsonIgnore
    private List<Operation> operationsFrom;

    @OneToMany(mappedBy = "to_wallet_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(
            action = org.hibernate.annotations.OnDeleteAction.CASCADE
    )
    @JsonIgnore
    private List<Operation> operationsTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Operation> getOperationsFrom() {
        return operationsFrom;
    }

    public void setOperationsFrom(List<Operation> operationsFrom) {
        this.operationsFrom = operationsFrom;
    }

    public List<Operation> getOperationsTo() {
        return operationsTo;
    }

    public void setOperationsTo(List<Operation> operationsTo) {
        this.operationsTo = operationsTo;
    }


    public Wallet() {
    }

    public Wallet(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

}