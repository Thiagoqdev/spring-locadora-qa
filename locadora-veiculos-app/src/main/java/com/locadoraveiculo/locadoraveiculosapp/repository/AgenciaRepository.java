package com.locadoraveiculo.locadoraveiculosapp.repository;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

}
