package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Usuario;
import com.locadoraveiculo.locadoraveiculosapp.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNome("Thiago");
        usuario.setEmail("thiago@mail.com");
        usuario.setCpf("12345678901");
        usuario.setSenha("senha123");
        usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
    }

    @Test
    void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.buscarUsuarioPorId(1L);

        assertTrue("Usuario deve estar presente", result.isPresent());
        assertEquals("Thiago", result.get().getNome());
    }
}