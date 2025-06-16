package com.keretaapi.user_service.repository;

import com.keretaapi.user_service.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}