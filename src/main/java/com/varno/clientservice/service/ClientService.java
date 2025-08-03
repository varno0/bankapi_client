package com.varno.clientservice.service;

import com.varno.clientservice.entities.Client;
import org.springframework.stereotype.Service;


@Service
public interface ClientService {

    Client createClient(Client client);
}
