package com.group.letscoding.dto.auth;

import com.group.letscoding.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @Size(min = 2, max = 20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .build();
    }
}
