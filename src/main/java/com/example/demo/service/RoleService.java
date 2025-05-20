package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    HttpEntity<?> getRoles();
}
