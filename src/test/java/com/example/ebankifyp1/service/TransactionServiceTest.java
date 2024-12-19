package com.example.ebankifyp1.service;

import com.example.ebankifyp1.exception.TransactionNotFoundException;
import com.example.ebankifyp1.model.Transaction;
import com.example.ebankifyp1.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction();
        transaction.setId(1L);
    }

    @Test
    public void testCreateTransaction() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        assertEquals(transaction.getId(), createdTransaction.getId());
    }

    @Test
    public void testGetTransactionById_TransactionFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        Transaction foundTransaction = transactionService.getTransactionById(1L);
        assertEquals(transaction.getId(), foundTransaction.getId());
    }

    @Test
    public void testGetTransactionById_TransactionNotFound() {
        when(transactionRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(2L));
        assertEquals("Transaction not found with id: 2", exception.getMessage());
    }
}