package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Usuario;
import com.locadoraveiculo.locadoraveiculosapp.model.Usuario.TipoUsuario;
import com.locadoraveiculo.locadoraveiculosapp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna uma lista paginada de todos os usuários")
    public ResponseEntity<Page<Usuario>> listarUsuarios(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        Page<Usuario> usuarios = usuarioService.listarUsuarios(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{usuarioId}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    public ResponseEntity<Usuario> buscarUsuarioPorId(
            @Parameter(description = "ID do usuário")
            @PathVariable Long usuarioId
    ) {
        return usuarioService.buscarUsuarioPorId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo")
    @Operation(summary = "Buscar usuários por tipo", description = "Retorna usuários filtrados por tipo")
    public ResponseEntity<Page<Usuario>> buscarUsuariosPorTipo(
            @RequestParam TipoUsuario tipoUsuario,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        Page<Usuario> usuarios = usuarioService.buscarUsuariosPorTipo(tipoUsuario, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{usuarioId}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<Usuario> atualizarUsuario(
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId,
            @Valid @RequestBody Usuario usuario
    ) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuarioId, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{usuarioId}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId
    ) {
        usuarioService.deletarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
}