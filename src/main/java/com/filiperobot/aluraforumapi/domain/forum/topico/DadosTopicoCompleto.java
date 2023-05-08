package com.filiperobot.aluraforumapi.domain.forum.topico;

import com.filiperobot.aluraforumapi.domain.course.DadosCursoCompleto;
import com.filiperobot.aluraforumapi.domain.user.DadosUsuarioCompleto;

import java.time.LocalDateTime;
import java.util.List;

public record DadosTopicoCompleto(Long id, String titulo, String mensagem,
                                  LocalDateTime dataCriacao, StatusTopico status,
                                  DadosUsuarioCompleto autor, DadosCursoCompleto curso) {
    public DadosTopicoCompleto(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getDadosAutor(),
                topico.getDadosCurso()
        );
    }
}
