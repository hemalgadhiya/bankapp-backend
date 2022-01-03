package com.example.demo.service;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.net.server.Client;

import com.example.demo.dao.*;
import com.example.demo.modal.*;

@Transactional
@Component
public class BankServiceImpl implements BankService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OperationDao operationDao;

    @Override
    public List<Operation> listOperations(int code){
        return operationDao.checkingAccount(code);
    }

    @Override
    public Operation deposit(int code, double amount){

        String deposit = "deposit";
        Account account = accountDao.findByCode(code).orElse(null);
        Credit credit = new Credit(new Date(), amount, deposit, account);
        account.setBalance(account.getBalance()+ amount);
        accountDao.save(account);
        return operationDao.save(credit);
    }
    
    @Override
    public Operation debit(int code, double amount){
        String debit = "debit";
        Account account = accountDao.findByCode(code).orElse(null);
        double checkout = 0;
        if ( account instanceof CheckingAccount){
            checkout = ((CheckingAccount) account).getLoan();
        }

        if (account.getBalance() + checkout < amount){
            throw new RuntimeException("Insufficient Balance");
        }

        Withdraw withDraw = new Withdraw(new Date(), amount, debit, account);

        account.setBalance(account.getBalance() - amount);
        accountDao.save(account);

        return operationDao.save(withDraw);

    }

    @Override
    public void depositOperation (int code, double amount){
        Account account = accountDao.findByCode(code).orElse(null);
        deposit(account.getCode(), amount);
    }

    @Override
    public void debitOperation(int code, double amount){
        Account account = accountDao.findByCode(code).orElse(null);
        debit(account.getCode(), amount);
    }

    @Override
    public User signUp(User user){
        return userDao.save(user);
    }

    @Override
    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount, long id){
        Random rnd = new Random();
        Integer out = rnd.nextInt(999999999);
        User user = userDao.findById(id).orElse(null);
        checkingAccount.setCreateDate(new Date());
        checkingAccount.setCode(out);
        user.addAccount(checkingAccount);

        return accountDao.save(checkingAccount);
    }

    // @Override
    // public Boolean signIn(User user){
    //     return true;

    // }

    @Override
    public List<User> findUsers(){
        return userDao.findAll();
    }

    @Override
    public Account getAccount(int code){
        return accountDao.findByCode(code).orElse(null);
    }

    @Override
    public List<Account> findAccountForUser(long id){
        User user = userDao.findById(id).orElse(null);
        return user.getAccounts();

    }

    @Override
    public User findUserByUserName(String userName){
        User user = userDao.findByusername(userName).orElse(null);
        return user;
    }
}
 