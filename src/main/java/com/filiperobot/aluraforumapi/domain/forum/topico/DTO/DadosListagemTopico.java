package com.filiperobot.aluraforumapi.domain.forum.topico.DTO;

import com.filiperobot.aluraforumapi.domain.course.DTO.DadosListagemCurso;
import com.filiperobot.aluraforumapi.domain.forum.topico.StatusTopico;
import com.filiperobot.aluraforumapi.domain.forum.topico.Topico;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosListagemUsuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(String titulo, String mensagem, LocalDateTime dataCriacao,
                                  StatusTopico status, DadosListagemUsuario autor, DadosListagemCurso curso) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DadosListagemUsuario(topico.getAutor()),
                new DadosListagemCurso(topico.getCurso())
        );
    }
}
