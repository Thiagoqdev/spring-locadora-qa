package com.locadoraveiculo.locadoraveiculosapp.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alugueis")
@Schema(description = "Representa um aluguel de veículo")
public class Aluguel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do aluguel", example = "1")
    private Long aluguel_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numeroAgencia")

    @Schema(description = "Agência responsável pelo aluguel")
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id")
    @Schema(description = "Veículo alugado")
    private Veiculo veiculo;

    @Schema(description = "Valor total cobrado pelo aluguel", example = "250.50")
    private BigDecimal valorCobrado;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de início do aluguel", example = "2024-01-15T10:30:00")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de fim do aluguel", example = "2024-01-20T18:00:00")
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de pagamento realizado")
    private TipoPagamento tipoPagamento;

    @Schema(description = "Tipos de pagamento disponíveis")
    public enum TipoPagamento {
        @Schema(description = "Pagamento à vista") AVISTA,
        @Schema(description = "Pagamento com cartão de crédito") CREDITO,
        @Schema(description = "Pagamento com cartão de débito") DEBITO
    }
}