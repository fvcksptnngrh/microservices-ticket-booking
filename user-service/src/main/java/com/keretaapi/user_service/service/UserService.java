package com.keretaapi.user_service.service;

import com.keretaapi.user_service.dto.UserProfileDto;
import com.keretaapi.user_service.entity.User;
import com.keretaapi.user_service.entity.UserProfile;
import com.keretaapi.user_service.repository.UserProfileRepository;
import com.keretaapi.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan dengan username: " + username));
    }

    @Transactional(readOnly = true)
    public UserProfileDto getUserProfile() {
        User currentUser = getCurrentUser();
        UserProfile profile = userProfileRepository.findById(currentUser.getId()).orElse(new UserProfile());

        UserProfileDto dto = new UserProfileDto();
        dto.setId(currentUser.getId());
        dto.setUsername(currentUser.getUsername());
        dto.setEmail(currentUser.getEmail());
        dto.setFullName(profile.getFullName());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setGender(profile.getGender());
        dto.setPhoneNumber(profile.getPhoneNumber());

        return dto;
    }

    // === LOGIKA DIPERBAIKI DI SINI ===
    @Transactional
    public UserProfileDto updateUserProfile(UserProfileDto profileUpdateRequest) {
        User currentUser = getCurrentUser();

        // Ambil profil dari User, atau buat baru jika belum ada
        UserProfile profile = currentUser.getUserProfile();
        if (profile == null) {
            profile = new UserProfile();
            profile.setUser(currentUser); // Kaitkan profil baru ini dengan user
            currentUser.setUserProfile(profile); // Kaitkan juga user dengan profil baru
        }

        // Update data dari DTO
        profile.setFullName(profileUpdateRequest.getFullName());
        profile.setDateOfBirth(profileUpdateRequest.getDateOfBirth());
        profile.setGender(profileUpdateRequest.getGender());
        profile.setPhoneNumber(profileUpdateRequest.getPhoneNumber());

        // Simpan entitas User utama. Karena ada cascade, UserProfile akan ikut tersimpan.
        userRepository.save(currentUser);

        return getUserProfile();
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}