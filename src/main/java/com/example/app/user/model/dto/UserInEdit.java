package com.example.app.user.model.dto;

import com.example.app.user.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInEdit {
    @NotBlank
    private String username;
    @NotBlank
    private String email; // TODO validation

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        return userEntity;
    }
}
