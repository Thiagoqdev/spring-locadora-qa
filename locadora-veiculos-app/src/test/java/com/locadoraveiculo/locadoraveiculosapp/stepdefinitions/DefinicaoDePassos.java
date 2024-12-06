package com.locadoraveiculo.locadoraveiculosapp.stepdefinitions;

import com.locadoraveiculo.locadoraveiculosapp.dto.UserRole;
import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.AgenciaRepository;
import com.locadoraveiculo.locadoraveiculosapp.repository.UserRepository;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
public class DefinicaoDePassos {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @Given("o banco de dados deve estar limpo")
    public void oBancoDeDadosEstaLimpo(){
        userRepository.deleteAll();
        agenciaRepository.deleteAll();
        veiculoRepository.deleteAll();
    }

    @When("cadastrar um novo usuário com nome {string}, com a senha {string} e o tipo de usuario {string}")
    public void cadastrarUmNovoUsuárioComNomeComASenhaEOTipoDeUsuario(String login, String password, String role) {

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        userEntity.setRole(UserRole.valueOf(role));

        userRepository.save(userEntity);
    }

    @Then("o usuario com nome {string} deve estar salvo no banco de dados")
    public void oUsuarioComNomeDeveEstarSalvoNoBancoDeDados(String login) {
        Optional<UserEntity> useroptional = userRepository.findByLogin(login);
        assertEquals(login, useroptional.get().getLogin());
    }

    @When("cadastrar uma nova agencia com numero {int} e nome {string} e salvar no banco de dados")
    public void cadastrarUmaNovaAgenciaComNumeroENomeESalvarNoBancoDeDados(long numero, String nomeAgencia) {

        Agencia agencia = new Agencia();

        agencia.setNumeroAgencia(numero);
        agencia.setNomeAgencia(nomeAgencia);
        agenciaRepository.save(agencia);
    }

    @Then("a agencia com nome {string} deve estar salvo no banco de dados")
    public void aAgenciaComNomeDeveEstarSalvoNoBancoDeDados(String nomeAgencia) {
        Optional<Agencia> result = agenciaRepository.findAgenciaByNomeAgencia(nomeAgencia);

        assertTrue("Agencia deve estar presente", result.isPresent());
        assertEquals(nomeAgencia, result.get().getNomeAgencia());
    }

    @When("cadastrar um novo veículo com marca {string} modelo {string} e ano de fabricacao {string} e salvar no banco de dados")
    public void cadastrarUmNovoVeículoComMarcaModeloEAnoDeFabricacaoESalvarNoBancoDeDados(String marca, String modelo, String anoFabricacao) {

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAnoFabricacao(anoFabricacao);
        veiculoRepository.save(veiculo);

    }

    @Then("o veiculo com id {int} deve estar salvo no banco de dados")
    public void oVeiculoComIdDeveEstarSalvoNoBancoDeDados(long id) {



    }

    @Then("o veiculo com id {int} e marca {string} deve estar salvo no banco de dados")
    public void oVeiculoComIdEMarcaDeveEstarSalvoNoBancoDeDados(long id, String marca) {

        Optional<Veiculo> result = veiculoRepository.findById(id);

        assertTrue("Agencia deve estar presente", result.isPresent());
        assertEquals(marca, result.get().getMarca());

    }
}
