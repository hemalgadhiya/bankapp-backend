package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.example.demo.modal.User;

public interface UserDao extends JpaRepository<User, Long>{

    Optional<User> findByusername(String userName);
    
}
