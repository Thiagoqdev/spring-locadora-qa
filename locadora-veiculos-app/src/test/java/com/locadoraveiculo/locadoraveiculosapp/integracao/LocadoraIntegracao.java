package com.locadoraveiculo.locadoraveiculosapp.integracao;

import com.locadoraveiculo.locadoraveiculosapp.dto.UserRole;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocadoraIntegracao {

    @Autowired
    TestRestTemplate restTemplate;

    private String Token;

    @Test
    @Order(1)
    @DisplayName("Deve registrar um novo usuário no banco de dados e logar recebendo o token JWT")
    void test01(){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test01");
        userEntity.setPassword("123456");
        userEntity.setRole(UserRole.valueOf("ADMIN"));

        ResponseEntity<String> ResponseEntityRegister = restTemplate.postForEntity("/auth/register", userEntity, String.class);

        assertEquals("Usuário registrado com sucesso", ResponseEntityRegister.getBody());

        ResponseEntity<String> ResponseEntityLogin = restTemplate.postForEntity("/auth/login", userEntity, String.class);

        assertEquals(200, ResponseEntityLogin.getStatusCodeValue());
        assertNotNull(ResponseEntityLogin.getBody());

        var result = ResponseEntityLogin.getBody();
        int doisPontosIndex = result.indexOf(":");
        this.Token = result.substring(doisPontosIndex + 1).replaceAll("[{}\"]", "");
    }

    @Test
    @Order(2)
    @DisplayName("Deve fazer consulta em veículos com o Token criado no test01")
    void test02(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.Token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> ResponseEntityVeiculos = restTemplate.exchange("/api/veiculos", HttpMethod.GET, entity, String.class);

        assertEquals(200, ResponseEntityVeiculos.getStatusCodeValue());

    }

    @Test
    @Order(3)
    @DisplayName("Deve fazer consulta em agencias com o Token criado no test01")
    void test03(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.Token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> ResponseEntityAgencias = restTemplate.exchange("/api/agencias", HttpMethod.GET, entity, String.class);

        assertEquals(200, ResponseEntityAgencias.getStatusCodeValue());

    }

    @Test
    @Order(4)
    @DisplayName("Deve fazer consulta em aluguel com o Token criado no test01")
    void test04(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.Token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> ResponseEntityAlugueis = restTemplate.exchange("/api/alugueis", HttpMethod.GET, entity, String.class);

        assertEquals(200, ResponseEntityAlugueis.getStatusCodeValue());
    }
}
