package com.example.demo.service;


import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public HttpEntity<?> login(LoginDTO loginDTO){
        var authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse(token, user.getId(), user.getUsername(), user.getPassword(), user.getFullName(), user.getRoles());
        return ResponseEntity.ok(authResponse);
    }
}
