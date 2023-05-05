package com.filiperobot.aluraforumapi.domain.course;

import jakarta.validation.constraints.NotBlank;

public record DadosCurso(@NotBlank String nome, @NotBlank String categoria) {
    public DadosCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }
}
