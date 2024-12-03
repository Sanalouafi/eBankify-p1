package com.example.ebankifyp1.dto;

import com.example.ebankifyp1.model.enums.AccountStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;

    @NotNull(message = "Balance is mandatory")
    private BigDecimal balance;

    private AccountStatus status;
}