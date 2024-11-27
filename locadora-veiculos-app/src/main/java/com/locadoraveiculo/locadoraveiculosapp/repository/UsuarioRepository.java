package com.locadoraveiculo.locadoraveiculosapp.repository;

import com.locadoraveiculo.locadoraveiculosapp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Page<Usuario> findByTipoUsuario(Usuario.TipoUsuario tipoUsuario, Pageable pageable);
}