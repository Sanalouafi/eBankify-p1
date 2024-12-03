package com.example.ebankifyp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Account extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
