package com.keretaapi.user_service.config;

import com.keretaapi.user_service.entity.Role;
import com.keretaapi.user_service.entity.User;
import com.keretaapi.user_service.repository.RoleRepository;
import com.keretaapi.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Buat ROLE_USER jika belum ada
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_USER");
            return roleRepository.save(newRole);
        });

        // 2. Buat ROLE_ADMIN jika belum ada
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_ADMIN");
            return roleRepository.save(newRole);
        });

        // 3. Buat user admin jika belum ada
        if (!userRepository.existsByUsername("admin_kai")) {
            User adminUser = new User();
            adminUser.setUsername("admin_kai");
            adminUser.setEmail("admin@keretaapi.com");
            // Enkripsi password menggunakan PasswordEncoder yang sama dengan saat login
            adminUser.setPassword(passwordEncoder.encode("passwordadmin"));
            adminUser.setRoles(Set.of(adminRole)); // Beri peran ADMIN
            userRepository.save(adminUser);
            System.out.println(">>> Akun admin 'admin_kai' berhasil dibuat.");
        }
    }
}