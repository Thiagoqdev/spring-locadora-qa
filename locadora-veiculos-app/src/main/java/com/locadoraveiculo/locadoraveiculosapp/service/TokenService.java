package com.locadoraveiculo.locadoraveiculosapp.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    final String ISSUER = "locadora-veiculos-app";

    @Value("${jwt-secret}")
    private String secret;

    public String generateToken(UserEntity user) throws JWTCreationException {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(ISSUER)
                    .withSubject(user.getLogin())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro while generating token", exception);
        }
    }

    public String validateToken(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while validate token", exception);
        }
    }


    private Instant getExpiration() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
