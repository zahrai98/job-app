package com.example.app.user.model.dto;

import com.example.app.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtOut {
    private String token;
    private long expiresIn;
}
