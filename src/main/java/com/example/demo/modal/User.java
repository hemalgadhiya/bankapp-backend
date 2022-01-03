package com.example.demo.modal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User(){
        super();
    }

    public User(String username, String password, String name, String email, List<Account> accounts) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.accounts = accounts;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUserName(){
        return username;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public void setAccount(List<Account> accounts){
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        if (getAccounts() == null){
            this.accounts = new ArrayList<>();
        }

        getAccounts().add(account);
        account.setUser(this);
    }




}
