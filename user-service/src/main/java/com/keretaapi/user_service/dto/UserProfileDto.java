package com.keretaapi.user_service.dto;

import java.time.LocalDate;

public class UserProfileDto {
    private String username;
    private String email;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}