package com.example.ebankifyp1.dto;

import com.example.ebankifyp1.model.enums.AccountStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;

    @NotNull(message = "Balance is mandatory")
    private BigDecimal balance;

    private AccountStatus status;
}