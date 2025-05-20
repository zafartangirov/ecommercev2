package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    HttpEntity<?> login(LoginDTO loginDTO);
}
