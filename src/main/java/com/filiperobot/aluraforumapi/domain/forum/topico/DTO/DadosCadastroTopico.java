package com.filiperobot.aluraforumapi.domain.forum.topico.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(String titulo, String mensagem, @NotNull Long autor, @NotNull Long curso) {
}
