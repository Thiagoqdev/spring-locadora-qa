package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Usuario;
import com.locadoraveiculo.locadoraveiculosapp.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        // Verificar se o email ou CPF já existem
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // Definir tipo de usuário padrão como CLIENTE se não especificado
        if (usuario.getTipoUsuario() == null) {
            usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    usuarioExistente.setCpf(usuarioAtualizado.getCpf());

                    // Manter o tipo de usuário original se não for especificado
                    if (usuarioAtualizado.getTipoUsuario() != null) {
                        usuarioExistente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
                    }

                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    @Transactional(readOnly = true)
    public Page<Usuario> buscarUsuariosPorTipo(Usuario.TipoUsuario tipoUsuario, Pageable pageable) {
        return usuarioRepository.findByTipoUsuario(tipoUsuario, pageable);
    }
}