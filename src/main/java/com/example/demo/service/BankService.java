package com.example.demo.service;

import java.util.List;
import com.example.demo.modal.*;

public interface BankService {

    Operation deposit(int code, double amount);

    Operation debit(int code, double amount);

    void debitOperation(int code, double amount);

    void depositOperation(int code, double amount);

    User signUp(User user);

    // Boolean signIn(User user);

    CheckingAccount addCheckingAccount(CheckingAccount checkingAccount, long id);

    List<Operation> listOperations(int code);

    List<User> findUsers();

    Account getAccount(int code);

    List<Account> findAccountForUser(long id);

    User findUserByUserName(String userName);








    
}
