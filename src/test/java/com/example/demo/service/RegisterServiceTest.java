package com.example.demo.service;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterService registerService;

    @Test
    public void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO("username", "password", "Full Name");

        Role role = new Role();
        when(roleRepository.findAll()).thenReturn(List.of(role));
        when(userRepository.save(any(User.class))).thenReturn(new User());

        ResponseEntity<?> response = (ResponseEntity<?>) registerService.register(registerDTO);
        assertEquals("Registered successfully", response.getBody());
    }
}
