package com.example.demo.modal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
    name = "account_Type")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private Integer code;
    private Date createDate;
    private double balance;

    @ManyToOne
    @JsonBackReference(value = "user")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations;

    public Account() {
        super();
    }

    public Account (Integer code, Date createDate, double balance, User user, List<Operation> operations) {
        super();
        this.code = code;
        this.createDate = createDate;
        this.balance = balance;
        this.user = user;
        this.operations = operations;
    }

    public Account (Integer code, Date createDate, double balance, User user) {
        super();
        this.code = code;
        this.createDate = createDate;
        this.balance = balance;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations){
        this.operations = operations;
    }

    public void doOperation( Operation operation) {
        if (getOperations() == null){
            this.operations = new ArrayList<>();
        }

        getOperations().add(operation);
        operation.setAccount(this);
    }
}
