package com.locadoraveiculo.locadoraveiculosapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registro de novo usuário")
public record RegisterDTO(
        @Schema(description = "Login do usuário", example = "joao.silva")
        String login,
        @Schema(description = "Senha do usuário", example = "senhaSegura123")
        String password,
        @Schema(description = "Role do usuário ADMIN ou USER")
        UserRole userRole
) {}