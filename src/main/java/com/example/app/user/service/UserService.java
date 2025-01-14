package com.example.app.user.service;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.CreateUserException;
import com.example.app.configs.exceptions.SystemException;
import com.example.app.user.model.UserEntity;
import com.example.app.user.model.dto.UserIn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.app.user.model.dto.UserOut;
import com.example.app.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOut getById(Long id) {
        return new UserOut(userRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", 404)));
    }

    public List<UserOut> getAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
        return userRepository.findAll(pageable).stream().map(UserOut::new).collect(Collectors.toList());
    }

    public UserOut getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new UserOut((UserEntity) authentication.getPrincipal());
    }

}
