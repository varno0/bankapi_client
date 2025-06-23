package com.varno.clientservice.controllers;

import com.varno.clientservice.dto.ClientDTO;
import com.varno.clientservice.entities.Client;
import com.varno.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String test(){
        return "test";
    }

}
