package com.example.demo.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modal.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
  Optional<Account> findByCode(Integer code);
}
