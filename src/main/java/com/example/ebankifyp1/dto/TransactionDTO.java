package com.example.ebankifyp1.dto;

import com.example.ebankifyp1.model.enums.TransactionStatus;
import com.example.ebankifyp1.model.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;

    @NotNull(message = "Account ID is mandatory")
    private Long accountId;

    @NotNull(message = "Transaction type is mandatory")
    private TransactionType transactionType;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    private LocalDateTime timestamp;

    private TransactionStatus status;

    private String description;
}