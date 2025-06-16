package com.keretaapi.user_service.controller;

import com.keretaapi.user_service.dto.UserProfileDto;
import com.keretaapi.user_service.entity.User;
import com.keretaapi.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint untuk ADMIN melihat semua user
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Endpoint BARU: Mendapatkan profil pengguna yang sedang login
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()") // Siapapun yang sudah login bisa akses
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        UserProfileDto userProfile = userService.getUserProfile();
        return ResponseEntity.ok(userProfile);
    }

    // Endpoint BARU: Memperbarui profil pengguna yang sedang login
    @PutMapping("/profile") // Menggunakan PUT lebih tepat untuk update
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfileDto> updateCurrentUserProfile(@Valid @RequestBody UserProfileDto profileDto) {
        UserProfileDto updatedProfile = userService.updateUserProfile(profileDto);
        return ResponseEntity.ok(updatedProfile);
    }
}