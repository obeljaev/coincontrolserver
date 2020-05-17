package ru.iambelyaev.coincontrolserver.hibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private int id;
    @Column(name = "sub_category_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
//    @org.hibernate.annotations.OnDelete(
//            action = org.hibernate.annotations.OnDeleteAction.CASCADE
//    )
//    private List<Operation> operations;

    public SubCategory() {
    }

    public SubCategory(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return id + " " + name;
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

//    public void removeOperation(Operation operation) {
//        operations.remove(operation);
//    }
//    public List<Operation> getOperations() {
//        return operations;
//    }
//    public void setOperations(List<Operation> operations) {
//        this.operations = operations;
//    }
}
