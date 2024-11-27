package com.locadoraveiculo.locadoraveiculosapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de autenticação do usuário")
public record UserDTO(
        @Schema(description = "Login do usuário", example = "usuario123")
        String login,

        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {}