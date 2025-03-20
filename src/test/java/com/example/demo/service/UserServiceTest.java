package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        List<Integer> roleIds = new ArrayList<>(List.of(1, 2));
        UserDTO userDTO = new UserDTO("username", "password", "fullName", roleIds);


        when(roleRepository.findByIdIn(userDTO.getRoleIds())).thenReturn(List.of());
        ResponseEntity<?> response = (ResponseEntity<?>) userService.createUser(userDTO);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        ResponseEntity<?> response = (ResponseEntity<?>) userService.getUsers(new User());
        assertNotNull(response.getBody());
    }
}
