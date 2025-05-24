package com.varno.clientservice.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.varno.clientservice.entities.Client;
import com.varno.clientservice.repositories.ClientRepositories;
import com.varno.clientservice.repositories.RoleRepositories;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements UserDetailsService{

    private final ClientRepositories clientRepositories;

    private final RoleRepositories roleRepositories;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepositories.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return new User(client.getUsername(), client.getPassword(),
         client.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
    }

}
