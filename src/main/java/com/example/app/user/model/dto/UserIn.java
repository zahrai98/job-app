package com.example.app.user.model.dto;

import com.example.app.user.model.UserEntity;
import com.example.app.user.validation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIn {
    private String username;
    @ValidPassword
    private String password; // TODO validation
    @NotBlank
    @NotNull
    @Email
    private String email;

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        return userEntity;
    }
}
