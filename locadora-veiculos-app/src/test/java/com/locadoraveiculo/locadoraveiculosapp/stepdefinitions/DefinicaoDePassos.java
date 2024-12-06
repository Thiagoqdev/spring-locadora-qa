package com.locadoraveiculo.locadoraveiculosapp.stepdefinitions;

import com.locadoraveiculo.locadoraveiculosapp.dto.UserRole;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import com.locadoraveiculo.locadoraveiculosapp.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
public class DefinicaoDePassos {

    @Autowired
    UserRepository userRepository;

    @Given("o banco de dados deve estar limpo")
    public void oBancoDeDadosEstaLimpo(){
        userRepository.deleteAll();
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
}
