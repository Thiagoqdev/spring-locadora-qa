package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
@Tag(name = "Aluguéis", description = "API para gerenciamento de aluguéis de veículos")
public class AluguelController {

    private final AluguelService aluguelService;

    @Autowired
    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    @Operation(summary = "Criar aluguel", description = "Cria um novo aluguel de veículo")
    public ResponseEntity<Aluguel> CriarAluguel(@RequestBody Aluguel aluguel) {
        Aluguel novoAluguel = aluguelService.CriarAluguel(aluguel);
        return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar aluguéis", description = "Retorna uma lista de todos os aluguéis")
    public ResponseEntity<List<Aluguel>> listarAlugueis() {
        List<Aluguel> aluguels = aluguelService.listarAluguels();
        return ResponseEntity.ok(aluguels);
    }

    @GetMapping("/aluguel_id")
    @Operation(summary = "Buscar aluguel por ID", description = "Retorna um aluguel específico pelo seu ID")
    public ResponseEntity<Aluguel> buscarAluguelPorCodigo(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        return aluguelService.buscarAluguelPorcodigo(aluguel_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{aluguel_id}")
    @Operation(summary = "Atualizar aluguel", description = "Atualiza os dados de um aluguel existente")
    public ResponseEntity<Aluguel> atualizarAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long veiculo_id, @RequestBody Aluguel aluguel) {
        try {
            Aluguel aluguelAtualizado = aluguelService.atualizarAluguel(veiculo_id, aluguel);
            return ResponseEntity.ok(aluguelAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{aluguel_id}")
    @Operation(summary = "Remover aluguel", description = "Remove um aluguel do sistema")
    public ResponseEntity<Void> removerAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        aluguelService.deletarAluguel(aluguel_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{aluguel_id}/finalizar")
    @Operation(summary = "Finalizar aluguel", description = "Finaliza um aluguel em andamento")
    public ResponseEntity<Aluguel> finalizarAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        Aluguel aluguelfinalizado = aluguelService.finalizarAluguel(aluguel_id);
        return ResponseEntity.ok(aluguelfinalizado);
    }

    @GetMapping("/alugueis/ativos")
    @Operation(summary = "Buscar aluguéis ativos", description = "Retorna uma lista de todos os aluguéis ativos")
    public ResponseEntity<List<Aluguel>> BuscarAluguelPorAtivos() {
        List<Aluguel> alugueisAtivos = aluguelService.buscarAlugueisAtivos();
        return ResponseEntity.ok(alugueisAtivos);
    }

    @GetMapping("alugueis/finalizados")
    @Operation(summary = "Buscar aluguéis finalizados", description = "Retorna uma lista de todos os aluguéis finalizados")
    public ResponseEntity<List<Aluguel>> BuscarAlugueisFinalizados() {
        List<Aluguel> alugueisFinalizados = aluguelService.buscarAlugueisFinalizados();
        return ResponseEntity.ok(alugueisFinalizados);
    }
}