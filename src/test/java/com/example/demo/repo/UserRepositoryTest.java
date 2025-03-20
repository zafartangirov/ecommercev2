package com.example.demo.repo;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> fetchedUser = userRepository.findByUsername("testuser");
        assertNotNull(fetchedUser.orElse(null));
    }
}
