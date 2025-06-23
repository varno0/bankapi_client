package com.varno.clientservice.service;

import java.util.Set;

import com.varno.clientservice.exceptons.UserAlreadyExist;
import com.varno.clientservice.repositories.RoleRepositories;
import org.springframework.stereotype.Service;

import com.varno.clientservice.entities.Client;
import com.varno.clientservice.entities.Role;
import com.varno.clientservice.repositories.ClientRepositories;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepositories clientRepositories;

    private final RoleRepositories roleRepositories;

    @Transactional
    public Client createClient(Client client) {
        if (clientRepositories.existsByUsername(client.getUsername())) {
            throw new UserAlreadyExist("Username already exists");
        }
        Role role = roleRepositories.findByName("ROLE_USER").orElseThrow(
                () -> new IllegalArgumentException("Role does not exist"));
        client.setRoles(Set.of(role));

        return clientRepositories.save(client);
    }
}
