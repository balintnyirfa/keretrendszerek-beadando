package com.keretrendszerek.beadando.service.impl;

import com.keretrendszerek.beadando.dto.UserDto;
import com.keretrendszerek.beadando.dto.UserDtoMapper;
import com.keretrendszerek.beadando.entity.Role;
import com.keretrendszerek.beadando.entity.User;
import com.keretrendszerek.beadando.repository.RoleRepository;
import com.keretrendszerek.beadando.repository.UserRepository;
import com.keretrendszerek.beadando.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserDtoMapper userDtoMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));


        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" user not found for id :: " + id);
        }
        return user;
    }


    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user -> mapToUserDto(user)))
                //.map(userDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Page<User> findUserPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
