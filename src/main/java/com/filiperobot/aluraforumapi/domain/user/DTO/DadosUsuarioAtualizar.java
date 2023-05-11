package com.filiperobot.aluraforumapi.domain.user.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosUsuarioAtualizar(@NotNull Long id, String nome, @Size(min = 4) String senha) {
}
