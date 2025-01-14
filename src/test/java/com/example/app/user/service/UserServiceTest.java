package com.example.app.user.service;

import com.example.app.user.model.UserEntity;
import com.example.app.user.model.dto.UserOut;
import com.example.app.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById() {
        Long userId  = 1L;
        UserEntity mockUser = new UserEntity();
        mockUser.setId(userId);
        mockUser.setUsername("username");
        mockUser.setPassword("password");
        mockUser.setEmail("email");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        UserOut result = userService.getById(1L);
        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("email", result.getEmail());
        assertEquals(userId, result.getId());

    }
}