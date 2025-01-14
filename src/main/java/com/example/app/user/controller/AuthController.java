package com.example.app.user.controller;


import com.example.app.configs.exceptions.CreateUserException;
import com.example.app.configs.security.JwtService;
import com.example.app.user.model.dto.JwtOut;
import com.example.app.user.model.dto.UserLoginDto;
import com.example.app.user.model.dto.UserOut;
import com.example.app.user.model.dto.UserSignUpDto;
import com.example.app.user.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    // TODO creste refresh token and extend token after expire time if user was using system
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtOut> authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        JwtOut loginResponse = authService.authenticateUser(userLoginDto);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserOut> registerUser(@Valid @RequestBody UserSignUpDto userSignUpDto) throws CreateUserException {
        UserOut registeredUser = authService.registerUser(userSignUpDto);

        return ResponseEntity.ok(registeredUser);
    }
}
