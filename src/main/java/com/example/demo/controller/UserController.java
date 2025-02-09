package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public HttpEntity<?> getUsers(@AuthenticationPrincipal User user){
        System.out.println(user);
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<?> createUser(@RequestBody UserDTO userDTO){
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

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id){
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

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}