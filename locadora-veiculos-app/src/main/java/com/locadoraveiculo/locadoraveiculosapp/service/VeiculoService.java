package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;


    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long veiculo_id) {
        return veiculoRepository.findById(veiculo_id);
    }

    public Veiculo atualizarVeiculo(Long veiculo_id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(veiculo_id)
                .map(veiculo -> {
                   veiculo.setMarca(veiculoAtualizado.getMarca());
                   veiculo.setModelo(veiculoAtualizado.getModelo());
                   veiculo.setAnoFabricacao(veiculoAtualizado.getAnoFabricacao());
                   return veiculoRepository.save(veiculo);
                        })
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com veiculo_id: " + veiculo_id));
    }

    public void deletarVeiculo(Long veiculo_id) {
        veiculoRepository.deleteById(veiculo_id);
    }
}
