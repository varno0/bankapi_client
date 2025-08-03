package com.varno.clientservice.repositories;

import com.varno.clientservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositories extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
