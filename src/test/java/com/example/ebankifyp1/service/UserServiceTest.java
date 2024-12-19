package com.example.ebankifyp1.service;

import com.example.ebankifyp1.exception.UserNotFoundException;
import com.example.ebankifyp1.model.User;
import com.example.ebankifyp1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
    }

    @Test
     void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
     void testDeleteUser_UserNotFound() {
        when(userRepository.existsById(2L)).thenReturn(false);
        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.deleteUser(2L));
        assertEquals("User not found with id: 2", exception.getMessage());
    }

    @Test
     void testGetUserById_UserFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getUserById(1L);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
     void testGetUserById_UserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(2L));
        assertEquals("User not found with id: 2", exception.getMessage());
    }
}