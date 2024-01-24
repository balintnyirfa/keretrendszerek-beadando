package com.keretrendszerek.beadando.service;

import com.keretrendszerek.beadando.dto.UserDto;
import com.keretrendszerek.beadando.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void deleteUserById(long id);
    User findUserByEmail(String email);
    User findUserById(long id);
    void updateUser(long id);
    List<UserDto> findAllUsers();
    Page<User> findUserPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
