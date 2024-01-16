package com.keretrendszerek.beadando.repository;

import com.keretrendszerek.beadando.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
