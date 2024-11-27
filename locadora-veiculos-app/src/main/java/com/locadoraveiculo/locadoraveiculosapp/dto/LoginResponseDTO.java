package com.locadoraveiculo.locadoraveiculosapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de login contendo token de autenticação")
public record LoginResponseDTO( @Schema(description = "Token JWT gerado", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")String token) {
}
