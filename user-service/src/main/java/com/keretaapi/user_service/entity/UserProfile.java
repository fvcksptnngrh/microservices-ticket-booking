package com.keretaapi.user_service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // Getters
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public User getUser() { return user; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setUser(User user) { this.user = user; }
}