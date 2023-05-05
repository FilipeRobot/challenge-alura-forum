package com.filiperobot.aluraforumapi.domain.course;

public record DadosCursoCompleto(Long id, String nome, String categoria) {
    public DadosCursoCompleto(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
