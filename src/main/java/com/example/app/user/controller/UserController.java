package com.example.app.user.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.CreateUserException;
import com.example.app.user.model.dto.UserIn;
import com.example.app.user.model.dto.UserOut;
import com.example.app.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "")
    public ResponseEntity<List<UserOut>> getAll(
            @Valid PageableDto pageableDto
    ) {
        List<UserOut> all = userService.getAll(pageableDto);
        return ResponseEntity.ok(all);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserOut> getById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }
}