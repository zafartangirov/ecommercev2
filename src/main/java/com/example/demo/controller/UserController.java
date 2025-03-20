package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public HttpEntity<?> getUsers(@AuthenticationPrincipal User user) {
        return userService.getUsers(user);
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Integer id) {
        return userService.getOneUserById(id);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        return userService.updateUser(userDTO, id);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}