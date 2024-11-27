package com.locadoraveiculo.locadoraveiculosapp.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "agencias")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Número único da agência", example = "1")
    private Long numeroAgencia;

    @Schema(description = "Nome da agência", example = "Locadora Top")
    private String nomeAgencia;

    @Schema(description = "Endereço completo da agência", example = "Av. Paulista, 1000 - São Paulo, SP")
    private String enderecoAgencia;

    @OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de aluguéis associados a esta agência")
    private List<Aluguel> alugueis = new ArrayList<>();
}

