package com.locadoraveiculo.locadoraveiculosapp.model;

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
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veiculo_id;

    @Column(unique = true)
    private String placa;

    private String modelo;
    private String marca;
    private String anoFabricacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aluguel")
    private StatusAluguel statusAluguel = StatusAluguel.DISPONIVEL;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluguel> alugueis = new ArrayList<>();


    public enum StatusAluguel {
        DISPONIVEL, ALUGADO
    }

    public enum TipoVeiculo {
        MOTO, CARRO, CAMINHAO
    }


}
