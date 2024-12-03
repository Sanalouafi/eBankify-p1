package com.example.ebankifyp1.mapper;

import com.example.ebankifyp1.dto.UserDTO;
import com.example.ebankifyp1.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
