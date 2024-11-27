package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.service.AgenciaService;
import com.locadoraveiculo.locadoraveiculosapp.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final AgenciaService agenciaService;
    private final VeiculoService veiculoService;

    @Autowired
    public HomeController(AgenciaService agenciaService, VeiculoService veiculoService) {
        this.agenciaService = agenciaService;
        this.veiculoService = veiculoService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        // Tipos de Veículos (usando o enum da classe Veiculo)
        List<String> tiposVeiculos = Arrays.asList(
                Veiculo.TipoVeiculo.MOTO.name(),
                Veiculo.TipoVeiculo.CARRO.name(),
                Veiculo.TipoVeiculo.CAMINHAO.name()
        );
        model.addAttribute("tiposVeiculos", tiposVeiculos);

        // Agências
        List<Agencia> agencias = agenciaService.listarAgencias();
        model.addAttribute("agencias", agencias);

        // Veículos disponíveis
        List<Veiculo> veiculosDisponiveis = veiculoService.listarVeiculos().stream()
                .filter(veiculo -> veiculo.getStatusAluguel() == Veiculo.StatusAluguel.DISPONIVEL)
                .collect(Collectors.toList());
        model.addAttribute("veiculosDisponiveis", veiculosDisponiveis);

        return "home";
    }
}