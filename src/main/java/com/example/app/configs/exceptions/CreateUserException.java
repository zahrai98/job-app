package com.example.app.configs.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CreateUserException extends Exception {
    HttpStatus status;
    Object message;
    int code;

    public CreateUserException(HttpStatus status, Object message, int code) {
        super(message != null ? message.toString() : null);
        this.status = status;
        this.message = message;
        this.code = code;
    }


    @Override
    public String getMessage() {
        return message != null ? message.toString() : null;
    }
}
