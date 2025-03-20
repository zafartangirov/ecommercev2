package com.example.demo.service;


import com.example.demo.repo.RoleRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public HttpEntity<?> getRoles(){
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
