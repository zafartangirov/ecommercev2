package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginService loginService;

    @Test
    public void testLogin() {
        LoginDTO loginDTO = new LoginDTO("username", "password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        HttpEntity<?> response = loginService.login(loginDTO);
        assertNotNull(response);
        assertTrue(response.getBody() instanceof AuthResponse);
    }
}