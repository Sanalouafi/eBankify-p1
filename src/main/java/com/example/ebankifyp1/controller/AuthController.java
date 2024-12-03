package com.example.ebankifyp1.controller;

import com.example.ebankifyp1.dto.UserDTO;
import com.example.ebankifyp1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserDTO userDTO) {
        String token = authService.authenticate(userDTO);
        return ResponseEntity.ok(token);
    }
}