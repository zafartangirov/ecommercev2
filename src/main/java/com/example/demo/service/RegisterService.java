package com.example.demo.service;


import com.example.demo.dto.RegisterDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {

    HttpEntity<?> register(RegisterDTO registerDTO);
}
