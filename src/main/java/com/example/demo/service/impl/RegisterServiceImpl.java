package com.example.demo.service.impl;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public HttpEntity<?> register(RegisterDTO registerDTO) {
        List<Role> allRoles = roleRepository.findAll();
        User user = User.builder()
                .roles(new ArrayList<>(List.of(allRoles.get(2))))
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .fullName(registerDTO.getFullName())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Registered successfully");
    }
}
