//package com.example.app.user.repository;
//
//import com.example.app.AppApplication;
//import com.example.app.user.model.UserEntity;
//import com.example.app.user.model.dto.UserIn;
//import com.example.app.user.model.dto.UserSignUpDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository underTestUserRepository;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//
////    @BeforeEach
////    void setUp() {
////
////    }
//
//    @AfterEach
//    void tearDown() {
//        underTestUserRepository.deleteAll();
//    }
//
//    @Test
//    void findByEmail() {
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        UserSignUpDto userSignUpDto = new UserSignUpDto("username", "password", "email@email.com");
//        UserEntity user = userSignUpDto.convertToEntity(null);
//        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
//        underTestUserRepository.save(user);
//
//        Optional<UserEntity> userEntity = underTestUserRepository.findByEmail(user.getEmail());
//
//        assertTrue(userEntity.isPresent());
//
//    }
////
////    @Test
////    void findByUsername() {
////    }
////
////    @Test
////    void existsByUsername() {
////    }
////
////    @Test
////    void existsByEmail() {
////    }
//}