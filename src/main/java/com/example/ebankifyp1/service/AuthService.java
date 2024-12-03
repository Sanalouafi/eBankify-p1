package com.example.ebankifyp1.service;

import com.example.ebankifyp1.dto.UserDTO;
import com.example.ebankifyp1.exception.InvalidCredentialsException;
import com.example.ebankifyp1.model.User;
import com.example.ebankifyp1.repository.UserRepository;
import com.example.ebankifyp1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public String authenticate(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
            return jwtProvider.generateToken(userDTO.getEmail());
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }
    }
}