package com.example.demo.dto;


import lombok.Value;

import java.util.List;

@Value
public class UserDTO {
    String username;
    String password;
    String fullName;
    List<Integer> roleIds;
}