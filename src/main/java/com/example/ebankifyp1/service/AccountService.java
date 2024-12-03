package com.example.ebankifyp1.service;

import com.example.ebankifyp1.exception.AccountNotFoundException;
import com.example.ebankifyp1.model.Account;
import com.example.ebankifyp1.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @PreAuthorize("hasRole('USER') and #account.user.id == authentication.principal.id")
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('EMPLOYEE') and #account.user.id == authentication.principal.id)")
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}