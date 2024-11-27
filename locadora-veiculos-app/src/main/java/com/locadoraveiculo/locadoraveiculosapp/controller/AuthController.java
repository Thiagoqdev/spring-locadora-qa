package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.dto.LoginResponseDTO;
import com.locadoraveiculo.locadoraveiculosapp.dto.RegisterDTO;
import com.locadoraveiculo.locadoraveiculosapp.dto.UserDTO;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import com.locadoraveiculo.locadoraveiculosapp.service.TokenService;
import com.locadoraveiculo.locadoraveiculosapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inexistente ou senha inválida");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.saveUser(registerDTO);
            return ResponseEntity.ok("Usuário registrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar usuário: " + e.getMessage());
        }
    }



}