package com.example.demo.modal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "operations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
    name = "Operation_Type")
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long numberOperation;
    private Date operationDate;
    private double amount;
    private String typeOperation;

    @ManyToOne
    @JsonBackReference(value = "account")
    private Account account;

    public Operation() {
        super();
    }

    public Operation(Date operationDate, double amount, String typeOperation, Account account) {
        super();
        this.operationDate = operationDate;
        this.amount = amount;
        this.typeOperation = typeOperation;
        this.account = account;
    }

    public long getNumberOperation() {
        return numberOperation;
    }

    public void setNumberOperation(long numberOperation){
        this.numberOperation = numberOperation;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate){
        this.operationDate = operationDate;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    


}
