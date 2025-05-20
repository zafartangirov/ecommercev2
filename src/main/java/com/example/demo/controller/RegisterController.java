package com.example.demo.controller;


import com.example.demo.dto.RegisterDTO;
import com.example.demo.service.RegisterService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public HttpEntity<?> register(@RequestBody RegisterDTO registerDTO){
        return registerService.register(registerDTO);
    }
}
