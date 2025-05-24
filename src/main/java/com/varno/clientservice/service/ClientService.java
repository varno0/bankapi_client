package com.varno.clientservice.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.varno.clientservice.entities.Client;
import com.varno.clientservice.entities.Role;
import com.varno.clientservice.repositories.ClientRepositories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositories clientRepositories;

    public Client createClient(String username, String password, String roleName) {
        if (clientRepositories.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        return clientRepositories.save(new Client(username, password, Set.of(new Role(roleName))));
    }
}
