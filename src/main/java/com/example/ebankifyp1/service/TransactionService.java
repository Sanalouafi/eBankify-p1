package com.example.ebankifyp1.service;

import com.example.ebankifyp1.exception.TransactionNotFoundException;
import com.example.ebankifyp1.model.Transaction;
import com.example.ebankifyp1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @PreAuthorize("hasRole('USER') and #transaction.account.user.id == authentication.principal.id")
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }
}