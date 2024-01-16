package com.keretrendszerek.beadando.repository;

import com.keretrendszerek.beadando.entitiy.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
