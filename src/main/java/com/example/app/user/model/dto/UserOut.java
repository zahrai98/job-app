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
public class UserOut {
    private Long id;
    private String username;
    private String email;

    public UserOut(UserEntity userEntity) {
        if (userEntity != null) {
            this.id = userEntity.getId();
            this.username = userEntity.getUsername();
            this.email = userEntity.getEmail();
        }
    }

}
