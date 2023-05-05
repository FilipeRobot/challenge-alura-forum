package com.filiperobot.aluraforumapi.domain.course;

public record DadosCurso(String nome, String categoria) {
    public DadosCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }
}
