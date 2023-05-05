package com.filiperobot.aluraforumapi.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosUsuarioAtualizar(@NotNull Long id, @NotBlank String nome, @NotBlank @Size(min = 4) String senha) {
}
