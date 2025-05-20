package com.example.demo.service.impl;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public HttpEntity<?> getUsers(User user) {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public User getOneUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }

    @Override
    public HttpEntity<?> createUser(UserDTO userDTO) {
        List<Role> roles = roleRepository.findByIdIn(userDTO.getRoleIds());
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .fullName(userDTO.getFullName())
                .roles(roles)
                .build();
        userRepository.save(user);
        return ResponseEntity.status(201).body(user);
    }

    @Override
    public HttpEntity<?> updateUser(UserDTO userDTO, Integer id) {
        List<Role> roles = roleRepository.findByIdIn(userDTO.getRoleIds());
        User user = User.builder()
                .id(id)
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .fullName(userDTO.getFullName())
                .roles(roles)
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
