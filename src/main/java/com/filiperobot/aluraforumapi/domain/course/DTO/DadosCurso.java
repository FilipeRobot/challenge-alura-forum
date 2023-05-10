package com.filiperobot.aluraforumapi.domain.course.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosCurso(@NotBlank String nome, @NotBlank String categoria) {
}
