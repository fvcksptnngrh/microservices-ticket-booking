package com.keretaapi.user_service.dto;

public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    // --- GETTERS DITAMBAHKAN MANUAL ---
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // --- SETTERS (untuk kelengkapan) ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}