package com.example.app.configs.exceptions;


import com.example.app.user.controller.AuthController;
import com.example.app.user.controller.UserController;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionControllerAdviceHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception exception) {
        return new ResponseEntity<>("An internal error happened" + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> noResourceFoundExceptionHandler(NoResourceFoundException exception) {
        return new ResponseEntity<>("Not found" + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> expiredJwtExceptionHandler(ExpiredJwtException exception) {
        return new ResponseEntity<>("The JWT token has expired" + exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsExceptionHandler(BadCredentialsException exception) {
        return new ResponseEntity<>("The username or password is incorrect " + exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<Object> accountStatusExceptionHandler(AccountStatusException exception) {
        return new ResponseEntity<>("The account is locked " + exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException exception) {
        return new ResponseEntity<>("You are not authorized to access this resource " + exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> signatureExceptionHandler(SignatureException exception) {
        return new ResponseEntity<>("The JWT signature is invalid" + exception.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerExceptionHandler(NullPointerException exception) {
        return new ResponseEntity<>("Null value error happened", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> illegalStateExceptionHandler(Exception exception) {
        return new ResponseEntity<>("An error happened" + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> systemExceptionHandler(SystemException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("details", exception.getMessage());
        errors.put("code", exception.getCode());
        return new ResponseEntity<>(errors, exception.getStatus());
    }

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<Object> createUserExceptionHandler(CreateUserException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("details", exception.getMessage());
        errors.put("code", exception.getCode());
        return new ResponseEntity<>(errors, exception.getStatus());
    }
}
