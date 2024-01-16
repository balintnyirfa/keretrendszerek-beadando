package com.keretrendszerek.beadando.service;

import com.keretrendszerek.beadando.dto.UserDto;
import com.keretrendszerek.beadando.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
