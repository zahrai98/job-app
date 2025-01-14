package com.example.app.user.repository;


import com.example.app.user.model.UserEntity;
import com.example.app.user.model.dto.UserSignUpDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTestUserRepository;


    @AfterEach
    void tearDown() {
        underTestUserRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        UserSignUpDto userSignUpDto = new UserSignUpDto("username", "password", "email@email.com");
        UserEntity user = userSignUpDto.convertToEntity(null);
        user.setPassword("password");
        underTestUserRepository.save(user);

        Optional<UserEntity> userEntity = underTestUserRepository.findByEmail(user.getEmail());

        assertTrue(userEntity.isPresent());
        assertEquals(user.getEmail(), userEntity.get().getEmail());
        assertEquals(user.getUsername(), userEntity.get().getUsername());

    }
}