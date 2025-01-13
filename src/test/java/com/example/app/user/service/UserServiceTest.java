//package com.example.app.user.service;
//
//import com.example.app.common.dto.PageableDto;
//import com.example.app.user.model.UserEntity;
//import com.example.app.user.model.dto.UserOut;
//import com.example.app.user.model.dto.UserSignUpDto;
//import com.example.app.user.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//    private UserService underTestUserService;
//
//    @BeforeEach
//    void setUp() {
//        underTestUserService = new UserService(userRepository);
//    }
//
//    @Test
//    void getById() {
//
//        Long id = 1L;
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(id);
//        userEntity.setUsername("username");
//        userEntity.setPassword("password");
//        userEntity.setEmail("email@email.com");
//
//        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
//
////        when(userRepository.findById(id)).thenReturn(Optional.of(user));
//
//
//        UserOut result = underTestUserService.getById(id);
//
//        assertNotNull(result);
//        assertEquals(id, result.getId());
//        assertEquals("username", result.getUsername());
//        assertEquals("email@email.com", result.getEmail());
//
//    }
//
////    @Test
////    void getAll() {
//////        ArgumentCaptor<Pageable> captorPage = ArgumentCaptor.forClass(Pageable.class);
////        PageableDto pageableDto = new PageableDto(10,1);
////        underTestUserService.getAll(pageableDto);
////        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
////        verify(userRepository).findAll(pageable);
////
////    }
//}