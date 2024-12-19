package com.example.ebankifyp1.service;

import com.example.ebankifyp1.exception.AccountNotFoundException;
import com.example.ebankifyp1.model.Account;
import com.example.ebankifyp1.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account();
        account.setId(1L);
    }

    @Test
     void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        Account createdAccount = accountService.createAccount(account);
        assertEquals(account.getId(), createdAccount.getId());
    }

    @Test
     void testGetAccountById_AccountFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Account foundAccount = accountService.getAccountById(1L);
        assertEquals(account.getId(), foundAccount.getId());
    }

    @Test
     void testGetAccountById_AccountNotFound() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(2L));
        assertEquals("Account not found with id: 2", exception.getMessage());
    }
}