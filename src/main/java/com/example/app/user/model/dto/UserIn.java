package com.example.app.user.model.dto;

import com.example.app.user.model.UserEntity;
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
    @NotBlank
    @NotNull
    @Size(min = 6, max = 20)
    private String password; // TODO validation
    @NotBlank
    @NotNull
    private String email; // TODO validation  send email

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        return userEntity;
    }
}
