package com.example.ebankifyp1.repository;

import com.example.ebankifyp1.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    AccountRepository findByAccountNumber(String accountNumber);
}
