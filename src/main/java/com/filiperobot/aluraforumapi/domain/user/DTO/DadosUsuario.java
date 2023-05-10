package com.filiperobot.aluraforumapi.domain.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosUsuario(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 4)
        String senha) {
}
