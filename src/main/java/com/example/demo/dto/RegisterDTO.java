package com.example.demo.dto;


import com.example.demo.entity.Role;
import lombok.Value;

import java.util.List;

@Value
public class RegisterDTO {
    String username;
    String password;
    String fullName;
}
