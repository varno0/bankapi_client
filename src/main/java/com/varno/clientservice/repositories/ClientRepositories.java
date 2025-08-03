package com.varno.clientservice.repositories;

import com.varno.clientservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositories extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    boolean existsByUsername(String username);
}
