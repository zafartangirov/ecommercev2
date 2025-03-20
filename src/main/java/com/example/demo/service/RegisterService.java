package com.example.demo.service;


import com.example.demo.dto.RegisterDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterService(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public HttpEntity<?> register(RegisterDTO registerDTO){
        List<Role> allRoles = roleRepository.findAll();
        User user = User.builder()
                .roles(new ArrayList<>(List.of(allRoles.get(0))))
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .fullName(registerDTO.getFullName())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Registered successfully");
    }
}
