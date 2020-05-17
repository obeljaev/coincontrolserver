package ru.iambelyaev.coincontrolserver.hibernate.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name")
    private String name;
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @org.hibernate.annotations.OnDelete(
            action = org.hibernate.annotations.OnDeleteAction.CASCADE
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(
            action = org.hibernate.annotations.OnDeleteAction.CASCADE
    )
    private List<Wallet> wallets;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        categories = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    public void addOperation(Operation operation) {
//        operation.setUser(this);
//        operations.add(operation);
//    }
//    public void removeOperation(Operation operation) {
//        operations.remove(operation);
//    }
//    public List<Operation> getOperations() {
//        return operations;
//    }
//    public void setOperations(List<Operation> operations) {
//        this.operations = operations;
//    }

    public void addCategory(Category category) {
        category.setUser(this);
        categories.add(category);
    }
    public void removeCategory(Category category) {
        categories.remove(category);
    }
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addWallet(Wallet wallet) {
        wallet.setUser(this);
        wallets.add(wallet);
    }
    public void removeWallet(Wallet wallet) {
        wallets.remove(wallet);
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}