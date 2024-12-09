package com.example.ebankifyp1.service;

import com.example.ebankifyp1.dto.UserDTO;
import com.example.ebankifyp1.exception.InvalidCredentialsException;
import com.example.ebankifyp1.model.User;
import com.example.ebankifyp1.repository.UserRepository;
import com.example.ebankifyp1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(UserDTO userDTO) {
        // Check if the email already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Email already in use.");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return "User registered successfully";
    }

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