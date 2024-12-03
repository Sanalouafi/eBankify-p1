package com.example.ebankifyp1.mapper;

import com.example.ebankifyp1.dto.AccountDTO;
import com.example.ebankifyp1.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);
    Account toAccount(AccountDTO accountDTO);
}
