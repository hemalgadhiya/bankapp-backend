package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.modal.Operation;

public interface OperationDao extends JpaRepository<Operation, Long> {
  @Query("select o from Operation o where o.account.code=:x order by o.operationDate desc")
  public List<Operation> checkingAccount(@Param("x") int id);
}
