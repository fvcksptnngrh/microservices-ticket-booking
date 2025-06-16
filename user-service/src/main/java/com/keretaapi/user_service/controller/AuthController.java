package com.keretaapi.user_service.controller;

import com.keretaapi.user_service.dto.AuthResponse;
import com.keretaapi.user_service.dto.LoginRequest;
import com.keretaapi.user_service.dto.MessageResponse;
import com.keretaapi.user_service.dto.RegisterRequest;
import com.keretaapi.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Endpoint khusus untuk otentikasi
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.registerUser(registerRequest);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Logika otentikasi penuh akan berfungsi setelah Spring Security dikonfigurasi
            AuthResponse response = authService.authenticateUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
             return ResponseEntity.status(401).body(new MessageResponse("Error: Invalid credentials!"));
        }
    }
}