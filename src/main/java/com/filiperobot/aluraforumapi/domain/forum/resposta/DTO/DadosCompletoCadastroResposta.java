package com.filiperobot.aluraforumapi.domain.forum.resposta.DTO;

import com.filiperobot.aluraforumapi.domain.forum.topico.Topico;
import com.filiperobot.aluraforumapi.domain.user.Usuario;

public record DadosCompletoCadastroResposta(String mensagem, Usuario autor, Topico topico) {
}
