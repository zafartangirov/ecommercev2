package com.example.demo.dto;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class AuthResponse {
    String token;
    Integer userId;
    String username;
    String password;
    String fullName;
    List<Role> roles;
}
