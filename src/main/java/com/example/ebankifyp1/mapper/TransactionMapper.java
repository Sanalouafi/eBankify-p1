package com.example.ebankifyp1.mapper;

import com.example.ebankifyp1.dto.TransactionDTO;
import com.example.ebankifyp1.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toDTO(Transaction transaction);
    Transaction fromDTO(TransactionDTO transactionDTO);
}
