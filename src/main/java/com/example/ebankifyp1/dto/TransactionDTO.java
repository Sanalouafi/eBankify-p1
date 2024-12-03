package com.example.ebankifyp1.dto;

import com.example.ebankifyp1.model.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    @NotNull
    private Long accountId;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private BigDecimal amount;

    private String description;
}