package com.example.ebankifyp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Transaction extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
