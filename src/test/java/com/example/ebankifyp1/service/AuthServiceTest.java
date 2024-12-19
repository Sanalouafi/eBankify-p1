package com.example.ebankifyp1.service;

import com.example.ebankifyp1.dto.UserDTO;
import com.example.ebankifyp1.exception.InvalidCredentialsException;
import com.example.ebankifyp1.model.User;
import com.example.ebankifyp1.repository.UserRepository;
import com.example.ebankifyp1.security.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        user = new User();
        user.setEmail("test@example.com");
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(user));
        Exception exception = assertThrows(InvalidCredentialsException.class, () -> authService.register(userDTO));
        assertEquals("Email already in use.", exception.getMessage());
    }

    @Test
    public void testRegister_Success() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        String result = authService.register(userDTO);
        assertEquals("User registered successfully", result);
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));
        Exception exception = assertThrows(InvalidCredentialsException.class, () -> authService.authenticate(userDTO));
        assertEquals("Invalid email or password.", exception.getMessage());
    }

    @Test
    public void testAuthenticate_Success() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(jwtProvider.generateToken(userDTO.getEmail())).thenReturn("token");
        String token = authService.authenticate(userDTO);
        assertEquals("token", token);
    }
}