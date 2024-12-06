package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import com.locadoraveiculo.locadoraveiculosapp.repository.AgenciaRepository;
import com.locadoraveiculo.locadoraveiculosapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserEntity user;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        user = new UserEntity();
        user.setId(1L);
        user.setLogin("Thiago");
    }

    @Test
    void testFindById() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserEntity> result = Optional.ofNullable(userService.findById(1L));

        assertTrue("User deve estar presente", result.isPresent());
        assertEquals("Thiago", result.get().getLogin());
    }
}