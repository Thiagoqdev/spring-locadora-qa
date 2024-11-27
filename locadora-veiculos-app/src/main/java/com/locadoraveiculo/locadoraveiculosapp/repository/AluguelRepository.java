package com.locadoraveiculo.locadoraveiculosapp.repository;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
