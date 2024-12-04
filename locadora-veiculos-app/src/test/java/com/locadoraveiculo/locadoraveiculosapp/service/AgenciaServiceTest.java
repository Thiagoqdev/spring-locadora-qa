package com.locadoraveiculo.locadoraveiculosapp.service;


import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.repository.AgenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = AgenciaService.class)
public class AgenciaServiceTest {
    @Mock
    private AgenciaRepository agenciaRepository;

    private Agencia agencia1;
    
    @InjectMocks
    private AgenciaService agenciaService;

    @BeforeEach
    void setUp() {
        agencia1 = new Agencia();
    }

    @Test
    void testAgenciaService() {
        
        agencia1.setNumeroAgencia(1L);
        agencia1.setNomeAgencia("Agencia1Teste");
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia1));
        
        Optional<Agencia> result = agenciaService.buscarAgenciaPorCodigo(1L);

        assertTrue("Agencia deve estar presente", result.isPresent());
        assertEquals("Agencia1Teste", result.get().getNomeAgencia());
    }
}
