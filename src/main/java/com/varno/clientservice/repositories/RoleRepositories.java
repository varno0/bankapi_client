package com.varno.clientservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varno.clientservice.entities.Role;

@Repository
public interface RoleRepositories extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
