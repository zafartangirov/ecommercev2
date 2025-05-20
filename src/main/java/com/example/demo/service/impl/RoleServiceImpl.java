package com.example.demo.service.impl;

import com.example.demo.repo.RoleRepository;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public HttpEntity<?> getRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
