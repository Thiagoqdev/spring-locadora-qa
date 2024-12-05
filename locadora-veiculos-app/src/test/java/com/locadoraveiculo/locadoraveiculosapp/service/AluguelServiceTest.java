package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.repository.AluguelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AluguelServiceTest {

    @Mock
    private AluguelRepository aluguelRepository;

    @InjectMocks
    private AluguelService aluguelService;

    private Aluguel aluguel1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aluguel1 = new Aluguel();
        aluguel1.setAluguel_id(1L);
        aluguel1.setValorCobrado(BigDecimal.valueOf(100.00));
    }

    @Test
    void testAluguelService() {
        when(aluguelRepository.findById(1L)).thenReturn(Optional.of(aluguel1));

        Optional<Aluguel> result = aluguelService.buscarAluguelPorcodigo(1L);

        assertTrue(result.isPresent(), "aluguel deve estar presente");
        assertEquals(BigDecimal.valueOf(100.00), result.get().getValorCobrado());
    }
}