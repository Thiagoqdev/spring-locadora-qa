package com.locadoraveiculo.locadoraveiculosapp.integracao;

import com.locadoraveiculo.locadoraveiculosapp.dto.UserRole;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocadoraIntegracao {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve registrar um novo usuário no banco de dados e logar recebendo o token JWT em seguida fazer consulta em veículos com o Token")
    void test01(){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test01");
        userEntity.setPassword("123456");
        userEntity.setRole(UserRole.valueOf("ADMIN"));

        ResponseEntity<String> ResponseEntityRegister = restTemplate.postForEntity("/auth/register", userEntity, String.class);

        assertEquals("Usuário registrado com sucesso", ResponseEntityRegister.getBody());

        ResponseEntity<String> ResponseEntityLogin = restTemplate.postForEntity("/auth/login", userEntity, String.class);

        var token = ResponseEntityLogin.getBody();
        int colonIndex = token.indexOf(":");
        String result = token.substring(colonIndex + 1);
        result = result.replaceAll("[{}\"]", "");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + result);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> ResponseEntityVeiculos = restTemplate.exchange("/api/veiculos", HttpMethod.GET, entity, String.class);

        assertEquals(200, ResponseEntityVeiculos.getStatusCodeValue());
    }

}
