package com.filiperobot.aluraforumapi.domain.forum.resposta;

import com.filiperobot.aluraforumapi.domain.forum.resposta.DTO.DadosAtualizarResposta;
import com.filiperobot.aluraforumapi.domain.forum.resposta.DTO.DadosCompletoCadastroResposta;
import com.filiperobot.aluraforumapi.domain.forum.topico.Topico;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "resposta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topico topico;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    private Boolean solucao = false;

    public Resposta(DadosCompletoCadastroResposta dados) {
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.topico = dados.topico();
    }

    public void atualizar(DadosAtualizarResposta respostaAtualizada){
        if (respostaAtualizada.mensagem() != null) {
            this.mensagem = respostaAtualizada.mensagem();
        }

        if (respostaAtualizada.solucao() != null) {
            this.solucao = respostaAtualizada.solucao();
        }
    }
}
