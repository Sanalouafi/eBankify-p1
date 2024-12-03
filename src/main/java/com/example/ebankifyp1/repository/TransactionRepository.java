package com.example.ebankifyp1.repository;

import com.example.ebankifyp1.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<TransactionRepository> findByAccount(AccountRepository account);
}
