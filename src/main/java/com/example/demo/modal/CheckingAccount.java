package com.example.demo.modal;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA")
public class CheckingAccount extends Account{

    private static final long serialVersionUID = 1L;

    private double loan;

    public CheckingAccount() {
        super();
    }
    
    public CheckingAccount(Integer code, Date createDate, double balance, User user, double loan){
        super(code, createDate, balance, user);
        this.loan = loan;
    }

    public double getLoan(){
        return loan;
    }

    public void setLoan(double loan){
        this.loan = loan; 
    }
}
