package com.locadoraveiculo.locadoraveiculosapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.locadoraveiculo.locadoraveiculosapp.model")
@EnableJpaRepositories("com.locadoraveiculo.locadoraveiculosapp.repository")
public class LocadoraVeiculosAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraVeiculosAppApplication.class, args);
	}
}