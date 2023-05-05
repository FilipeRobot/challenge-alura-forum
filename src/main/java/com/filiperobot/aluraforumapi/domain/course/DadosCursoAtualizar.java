package com.filiperobot.aluraforumapi.domain.course;

import jakarta.validation.constraints.NotNull;

public record DadosCursoAtualizar(@NotNull Long id, String nome, String categoria) {
}
