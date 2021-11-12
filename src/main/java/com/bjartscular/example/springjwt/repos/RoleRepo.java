package com.bjartscular.example.springjwt.repos;

import com.bjartscular.example.springjwt.model.ERole;
import com.bjartscular.example.springjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
