package com.keretrendszerek.beadando.dto;

import com.keretrendszerek.beadando.entity.User;

import java.util.function.Function;

public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
