package com.locadoraveiculo.locadoraveiculosapp.service;


import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaService {

    private AgenciaRepository agenciaRepository;

    @Autowired
    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public Agencia criarAgencia(Agencia agencia) {
        return agenciaRepository.save(agencia);
    }

    public List<Agencia> listarAgencias() {
        return agenciaRepository.findAll();
    }

    public Optional<Agencia> buscarAgenciaPorCodigo(Long numeroAgencia) {
        return agenciaRepository.findById(numeroAgencia);
    }

    public Agencia atualizarAgencia(Long numeroAgencia, Agencia agenciaAtualizada) {
        return agenciaRepository.findById(numeroAgencia)
                .map(agenciaExistente -> {
                    agenciaExistente.setNomeAgencia(agenciaAtualizada.getNomeAgencia());
                    agenciaExistente.setEnderecoAgencia(agenciaAtualizada.getEnderecoAgencia());

                    return agenciaRepository.save(agenciaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Agência não encontrada com o número: " + numeroAgencia));
    }

    public void removerAgencia(Long numeroAgencia) {
        agenciaRepository.deleteById(numeroAgencia);
    }
}
