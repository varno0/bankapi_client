package com.varno.clientservice.controllers;

import com.varno.clientservice.dto.ClientDTO;
import com.varno.clientservice.dto.ExceptionDTO;
import com.varno.clientservice.dto.JwtResponse;
import com.varno.clientservice.entities.Client;
import com.varno.clientservice.exceptons.UserAlreadyExist;
import com.varno.clientservice.service.ClientService;
import com.varno.clientservice.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtils jwtTokenUtils;

    private final ModelMapper mapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody ClientDTO clientDTO) {

        UserDetails userDetails = (UserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clientDTO.getUsername(),
                        clientDTO.getPassword())).getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwtTokenUtils.generateToken(userDetails)));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrationClient(@RequestBody ClientDTO clientDTO) {

        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        Client client = mapper.map(clientDTO, Client.class);
        ClientDTO savedClient = mapper.map(clientService.createClient(client), ClientDTO.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedClient);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handlerBadCredential(BadCredentialsException e) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<?> handlerUserAlreadyExist(UserAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

}
