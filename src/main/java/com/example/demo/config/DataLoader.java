package com.example.demo.config;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public DataLoader(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> allRoles = roleRepository.findAll();
        User user1 = User.builder()
                .roles(new ArrayList<>(List.of(allRoles.get(0))))
                .username("a")
                .password(passwordEncoder.encode("root123"))
                .fullName("Eshmat Toshmatov")
                .build();
        userRepository.save(user1);
        User user2 = User.builder()
                .roles(new ArrayList<>(List.of(allRoles.get(1))))
                .username("b")
                .password(passwordEncoder.encode("root123"))
                .fullName("Hikmat Nusratov")
                .build();
        userRepository.save(user2);
    }
}
