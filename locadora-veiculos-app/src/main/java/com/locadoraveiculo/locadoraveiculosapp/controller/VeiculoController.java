package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@Tag(name = "Veículos", description = "API para gerenciamento de veículos")
public class VeiculoController {

    private VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    @Operation(summary = "Criar veículo", description = "Cria um novo veículo")
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar veículos", description = "Retorna uma lista de todos os veículos")
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{veiculo_id}")
    @Operation(summary = "Buscar veículo por ID", description = "Retorna um veículo específico pelo seu ID")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@Parameter(description = "ID do veículo") @PathVariable Long veiculo_id) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{veiculo_id}")
    @Operation(summary = "Atualizar veículo", description = "Atualiza os dados de um veículo existente")
    public ResponseEntity<Veiculo> atualizarVeiculo(@Parameter(description = "ID do veículo") @PathVariable Long veiculo_id, @RequestBody Veiculo veiculo) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(veiculoExistente -> {
                    Veiculo veiculoAtulizado = veiculoService.atualizarVeiculo(veiculo_id, veiculo);
                    return ResponseEntity.ok(veiculoAtulizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{veiculo_id}")
    @Operation(summary = "Deletar veículo", description = "Remove um veículo do sistema")
    public ResponseEntity<Void> deletarVeiculo(@Parameter(description = "ID do veículo") @PathVariable Long veiculo_id) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(veiculo -> {
                    veiculoService.deletarVeiculo(veiculo_id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}