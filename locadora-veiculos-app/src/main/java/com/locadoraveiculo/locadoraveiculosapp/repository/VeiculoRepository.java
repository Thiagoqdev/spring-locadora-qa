package com.locadoraveiculo.locadoraveiculosapp.repository;

import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {


}
