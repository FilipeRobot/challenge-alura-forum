package com.filiperobot.aluraforumapi.domain.forum.topico;

import com.filiperobot.aluraforumapi.domain.course.Curso;
import com.filiperobot.aluraforumapi.domain.user.Usuario;

public record DadosCompletoCadastroTopico(String titulo, String mensagem, Usuario autor, Curso curso) {
}
