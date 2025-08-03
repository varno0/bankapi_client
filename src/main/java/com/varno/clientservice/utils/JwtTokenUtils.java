package com.varno.clientservice.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.lifeTime}")
    private Duration lifeTime;

    public String generateToken(UserDetails userDetails) {

        List<String> roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList();

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", roles)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(lifeTime))
                .sign(Algorithm.HMAC256(jwtSecret));

    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return JWT.decode(token).getClaim("roles").asList(String.class);
    }

}
