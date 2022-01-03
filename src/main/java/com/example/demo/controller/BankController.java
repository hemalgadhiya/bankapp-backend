package com.example.demo.controller;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.dao.DataIntegrityViolationException;
import com.example.demo.modal.*;
import com.example.demo.dao.*;
import com.example.demo.service.*;
import org.springframework.http.*;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountDao accountDao;

    // @PostMapping("/signup")
    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    public ResponseEntity<User> signUp(@RequestBody User user){
        try {
            ResponseEntity<User> response = ResponseEntity.status(HttpStatus.CREATED).body(bankService.signUp(user));
            return response;
        }
        catch( DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    // @RequestMapping(method = RequestMethod.POST, value = "/signin", produces = "application/json")
    // public ResponseEntity<String> signin(@)

    @RequestMapping(method = RequestMethod.GET, value="/health", produces = "application/json")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.status(HttpStatus.OK).body("{\"RESPONSE\" : \"Health check successsfull\"}");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAccount/{id}", produces = "application/json")
    public ResponseEntity<CheckingAccount> addCheckingAccount(@RequestBody CheckingAccount checkingAccount, @PathVariable long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(bankService.addCheckingAccount(checkingAccount, id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deposit/{code}")
    public ResponseEntity<String> deposit(@RequestBody Operation operation, @PathVariable int code){
            bankService.depositOperation(code, operation.getAmount());
            return ResponseEntity.status(HttpStatus.OK).body("{\"RESPONSE\" : \"Deposit successsfull\"}");
        
    }

    @RequestMapping(method = RequestMethod.POST, value = "/debit/{code}")
    public ResponseEntity<String> debit(@RequestBody Operation operation, @PathVariable int code){
        try{
            bankService.debitOperation(code, operation.getAmount());
            return ResponseEntity.status(HttpStatus.OK).body("{\"RESPONSE\" : \"Debit successsfull\"}");
        }
        catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"RESPONSE\" : \"Insufficient funds\"}");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAccount/{code}")
    public ResponseEntity<Account> getAccount(@PathVariable int code){
        return ResponseEntity.status(HttpStatus.OK).body(bankService.getAccount(code));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getAccountsByUser/{id}")
    public ResponseEntity<List<Account>> getAccountForUser(@PathVariable long id){
    //     HttpHeaders responseHeaders = new HttpHeaders();
    //         responseHeaders.set("Access-Control-Allow-Origin", 
    //   "*");
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findAccountForUser(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listOperations/{code}")
    public ResponseEntity<List<Operation>> listOperations(@PathVariable int code){
        return ResponseEntity.status(HttpStatus.OK).body(bankService.listOperations(code));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<User> getUser(@RequestBody User user){
        try{
            User user2 = bankService.findUserByUserName(user.getUserName());
            
            if (user2.getPassword().equals(user.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body(user2);
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        }
        catch (NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        
        
    }


    
}
