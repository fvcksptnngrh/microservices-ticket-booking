package com.keretaapi.user_service.dto;

public class LoginRequest {
    private String username;
    private String password;

    // --- GETTERS DITAMBAHKAN MANUAL ---
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // --- SETTERS (untuk kelengkapan) ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}