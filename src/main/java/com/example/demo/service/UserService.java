package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    HttpEntity<?> getUsers(User user);

    User getOneUserById(Integer id);

    HttpEntity<?> createUser(UserDTO userDTO);

    HttpEntity<?> updateUser(UserDTO userDTO, Integer id);

    void deleteUser(Integer id);
}
