package com.filiperobot.aluraforumapi.domain.forum.topico.DTO;

import com.filiperobot.aluraforumapi.domain.forum.topico.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(@NotNull Long id, String titulo, String mensagem, @NotNull StatusTopico status) {
}
