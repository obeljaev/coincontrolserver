package ru.iambelyaev.coincontrolserver.hibernate.models;

import javax.persistence.*;
import ru.iambelyaev.coincontrolserver.hibernate.models.User;

import java.util.List;

@Entity
@Table (name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private int money;

//    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
//    @org.hibernate.annotations.OnDelete(
//            action = org.hibernate.annotations.OnDeleteAction.CASCADE
//    )
//    private List<Operation> operations;


    public Wallet() {
    }

    public Wallet(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }

//    public void removeOperation(Operation operation) {
//        operations.remove(operation);
//    }
//    public List<Operation> getOperations() {
//        return operations;
//    }
//    public void setOperations(List<Operation> operations) {
//        this.operations = operations;
//    }

    @Override
    public String toString() {
        return id + " " + user.getId() + " " + name + " " + money;
    }
}
