package com.keretaapi.user_service.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // Import yang benar
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keretaapi.user_service.dto.AuthResponse;
import com.keretaapi.user_service.dto.LoginRequest;
import com.keretaapi.user_service.dto.RegisterRequest;
import com.keretaapi.user_service.entity.Role;
import com.keretaapi.user_service.entity.User;
import com.keretaapi.user_service.repository.RoleRepository;
import com.keretaapi.user_service.repository.UserRepository;
import com.keretaapi.user_service.security.JwtUtils;


@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils; // Sekarang diaktifkan

    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Error: Username sudah digunakan!");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: Email sudah digunakan!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role 'ROLE_USER' tidak ditemukan."));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication); // Membuat token JWT asli

        User user = userRepository.findByUsername(loginRequest.getUsername()).get();

        return new AuthResponse(jwt, user.getId(), user.getUsername());
    }
}