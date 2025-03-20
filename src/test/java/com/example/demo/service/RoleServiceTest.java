package com.example.demo.service;

import com.example.demo.repo.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testGetRoles() {
        when(roleRepository.findAll()).thenReturn(List.of());
        ResponseEntity<?> response = (ResponseEntity<?>) roleService.getRoles();
        assertNotNull(response.getBody());
    }
}
