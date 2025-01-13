package com.example.app.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.app.configs.exceptions.CreateUserException;

import com.example.app.configs.security.JwtService;
import com.example.app.user.model.UserEntity;
import com.example.app.user.model.dto.JwtOut;
import com.example.app.user.model.dto.UserLoginDto;
import com.example.app.user.model.dto.UserOut;
import com.example.app.user.model.dto.UserSignUpDto;
import com.example.app.user.repository.UserRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public JwtOut authenticateUser(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getUsername(),
                        userLoginDto.getPassword()
                )
        );
        UserEntity user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new JwtOut(jwtToken, jwtService.getExpirationTime());


//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//        return new JwtOut(jwt);
    }

    public UserOut registerUser(UserSignUpDto userSignUpDto) throws CreateUserException {
        if (userRepository.existsByUsername(userSignUpDto.getUsername())) {
            throw new CreateUserException(HttpStatus.CONFLICT, "username already exist", 409);
        }
        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            throw new CreateUserException(HttpStatus.CONFLICT, "email already exist", 409);
        }

        UserEntity user = userSignUpDto.convertToEntity(null);
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        userRepository.save(user);
        return new UserOut(user);
    }
}