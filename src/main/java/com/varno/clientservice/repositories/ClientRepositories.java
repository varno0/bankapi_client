package com.varno.clientservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varno.clientservice.entities.Client;

@Repository
public interface ClientRepositories  extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    boolean existsByUsername(String username);
}
