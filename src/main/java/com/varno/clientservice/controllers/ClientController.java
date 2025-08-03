package com.varno.clientservice.controllers;

import com.varno.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String test() {
        return "test";
    }

}
