package com.smartjob.reto.adapter.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtConfig {
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    public String generateToken(String name, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name);
        claims.put("roles", List.of("ROLE_USER"));
        claims.put("email", email);

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .claims(claims)
                .subject(UUID.randomUUID().toString())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .id(UUID.randomUUID().toString())
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
