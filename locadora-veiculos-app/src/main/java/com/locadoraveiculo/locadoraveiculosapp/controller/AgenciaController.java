package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.service.AgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencias")
@Tag(name = "Agências", description = "API para gerenciamento de agências")
public class AgenciaController {

    private final AgenciaService agenciaService;

    @Autowired
    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @PostMapping
    @Operation(summary = "Criar agência", description = "Cria uma nova agência")
    public ResponseEntity<Agencia> criarAgencia(@RequestBody Agencia agencia) {
        Agencia novaAgencia = agenciaService.criarAgencia(agencia);
        return new ResponseEntity<>(novaAgencia, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar agências", description = "Retorna uma lista de todas as agências")
    public ResponseEntity<List<Agencia>> listarAgencias() {
        List<Agencia> agencias = agenciaService.listarAgencias();
        return ResponseEntity.ok(agencias);
    }

    @GetMapping("/{numeroAgencia}")
    @Operation(summary = "Buscar agência por ID", description = "Retorna uma agência específica pelo seu número")
    public ResponseEntity<Agencia> buscarAgenciaPorId(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroAgencia}")
    @Operation(summary = "Atualizar agência", description = "Atualiza os dados de uma agência existente")
    public ResponseEntity<Agencia> atualizarAgencia(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia, @RequestBody Agencia agencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(agenciaExistente -> {
                    agenciaExistente.setNomeAgencia(agencia.getNomeAgencia());
                    agenciaExistente.setEnderecoAgencia(agencia.getEnderecoAgencia());

                    Agencia agenciaAtualizada = agenciaService.atualizarAgencia(numeroAgencia, agenciaExistente);
                    return ResponseEntity.ok(agenciaAtualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{numeroAgencia}")
    @Operation(summary = "Deletar agência", description = "Remove uma agência do sistema")
    public ResponseEntity<Void> DeletarAgencia(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(agencia -> {
                    agenciaService.removerAgencia(numeroAgencia);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}